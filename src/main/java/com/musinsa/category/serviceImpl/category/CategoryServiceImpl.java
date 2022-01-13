package com.musinsa.category.serviceImpl.category;

import com.musinsa.category.domain.category.CategoryRequest;
import com.musinsa.category.domain.category.CategoryResponse;
import com.musinsa.category.entity.category.CategoryEntity;
import com.musinsa.category.enums.ErrorMessage;
import com.musinsa.category.exception.staticException.RequestInputException;
import com.musinsa.category.mapper.category.CategoryMapper;
import com.musinsa.category.repository.category.CategoryRepository;
import com.musinsa.category.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    @Override
    public List<CategoryResponse> getCategory() {
        return categoryRepository.findRootCategory().stream().map(CategoryMapper.INSTANCE::toCategoryResponse).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public CategoryResponse getCategoryById(Long categoryId) {
        return CategoryMapper.INSTANCE.toCategoryResponse(this.getCategoryEntity(categoryId));
    }

    @Transactional
    @Override
    public CategoryResponse postCategory(CategoryRequest categoryRequest) {
        // set root if parentCategoryId is null
        CategoryEntity categoryEntity = new CategoryEntity(categoryRequest);

        if (categoryRequest.getParentCategoryId() != null)
            categoryEntity.setParentCategoryEntity(this.getCategoryEntity(categoryRequest.getParentCategoryId()));

        categoryRepository.save(categoryEntity);

        return CategoryMapper.INSTANCE.toCategoryResponse(categoryEntity);
    }

    @Transactional
    @Override
    public CategoryResponse updateCategory(Long categoryId, CategoryRequest categoryRequest) {
        CategoryEntity categoryEntity = this.getCategoryEntity(categoryId); // 계층이동은 금지한다
        categoryEntity.setCategoryName(categoryRequest.getCategoryName());
        categoryRepository.saveAndFlush(categoryEntity);
        return CategoryMapper.INSTANCE.toCategoryResponse(categoryEntity);
    }

    @Transactional
    @Override
    public void deleteCategory(Long categoryId) {
        // category delete only allowed when it has not children
        if (!this.getCategoryEntity(categoryId).getChildCategoryEntities().isEmpty())
            throw new RequestInputException(ErrorMessage.CHILD_CATEGORY_EXIST_EXCEPTION, false);
        categoryRepository.delete(this.getCategoryEntity(categoryId)); // note! soft delete
    }
    
    private CategoryEntity getCategoryEntity(Long categoryId) {
        Optional<CategoryEntity> optionalCategoryEntity = categoryRepository.findById(categoryId);
        if (optionalCategoryEntity.isEmpty())
            throw new RequestInputException(ErrorMessage.CATEGORY_NOT_EXIST_EXCEPTION, false);
        return optionalCategoryEntity.get();
    }
}
