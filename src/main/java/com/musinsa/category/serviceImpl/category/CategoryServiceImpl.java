package com.musinsa.category.serviceImpl.category;

import com.musinsa.category.domain.category.CategoryRequest;
import com.musinsa.category.domain.category.CategoryResponse;
import com.musinsa.category.entity.category.CategoryEntity;
import com.musinsa.category.enums.ErrorMessage;
import com.musinsa.category.exception.staticException.RequestInputException;
import com.musinsa.category.mapper.category.CategoryMapper;
import com.musinsa.category.repository.category.CategoryRepository;
import com.musinsa.category.service.category.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    @Override
    public List<CategoryResponse> getCategory() {
        return categoryRepository.findRootCategory().stream().map(CategoryMapper.INSTANCE::toCategoryResponse).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public CategoryResponse getCategoryById(Long categoryId) {
        Optional<CategoryEntity> optionalCategoryEntity = categoryRepository.findById(categoryId);
        if (optionalCategoryEntity.isEmpty())
            throw new RequestInputException(ErrorMessage.CATEGORY_NOT_EXIST_EXCEPTION, false);

        return CategoryMapper.INSTANCE.toCategoryResponse(optionalCategoryEntity.get());
    }

    @Override
    public void postCategory(CategoryRequest categoryRequest) {
        if (categoryRequest.getParentCategoryId() == null) {
            categoryRepository.save(new CategoryEntity(categoryRequest.getCategoryName(), null));
            return;
        }

        Optional<CategoryEntity> optionalParentCategoryEntity = categoryRepository.findById(categoryRequest.getParentCategoryId());
        if (optionalParentCategoryEntity.isEmpty())
            throw new RequestInputException(ErrorMessage.PARENT_CATEGORY_NOT_EXIST_EXCEPTION, false);

        categoryRepository.save(new CategoryEntity(categoryRequest.getCategoryName(), optionalParentCategoryEntity.get())); // set parentCategory if exists
    }

    @Transactional
    @Override
    public void updateCategory(Long categoryId, CategoryRequest categoryRequest) {
        Optional<CategoryEntity> optionalCategoryEntity = categoryRepository.findById(categoryId);
        if (optionalCategoryEntity.isEmpty())
            throw new RequestInputException(ErrorMessage.CATEGORY_NOT_EXIST_EXCEPTION, false);

        optionalCategoryEntity.get().setCategoryName(categoryRequest.getCategoryName());
        if (categoryRequest.getParentCategoryId() == null) {
            optionalCategoryEntity.get().setParentCategoryEntity(null); // if value null, set root category
            return;
        }

        if(categoryRequest.getParentCategoryId() == categoryId)
            throw new RequestInputException(ErrorMessage.PARENT_CATEGORY_VALUE_EXCEPTION, false);

        Optional<CategoryEntity> optionalParentCategoryEntity = categoryRepository.findById(categoryRequest.getParentCategoryId());
        if (optionalParentCategoryEntity.isEmpty())
            throw new RequestInputException(ErrorMessage.PARENT_CATEGORY_NOT_EXIST_EXCEPTION, false);

        optionalCategoryEntity.get().setParentCategoryEntity(optionalParentCategoryEntity.get()); // change or maintain parentCategory if value exists
    }

    @Transactional
    @Override
    public void deleteCategory(Long categoryId) {
        Optional<CategoryEntity> optionalCategoryEntity = categoryRepository.findById(categoryId);
        if (optionalCategoryEntity.isEmpty())
            throw new RequestInputException(ErrorMessage.CATEGORY_NOT_EXIST_EXCEPTION, false);

        // note! soft delete cascade
        categoryRepository.delete(optionalCategoryEntity.get());

    }
}
