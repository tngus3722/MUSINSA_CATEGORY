package com.musinsa.category.serviceImpl.category;

import com.musinsa.category.domain.response.CategoryResponse;
import com.musinsa.category.entity.category.CategoryEntity;
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
    public CategoryResponse getCategoryById(Long categoryId) throws Exception {
        Optional<CategoryEntity> optionalCategoryEntity = categoryRepository.findById(categoryId);
        if (optionalCategoryEntity.isEmpty())
            throw new Exception("zz"); // TODO custom

        return CategoryMapper.INSTANCE.toCategoryResponse(optionalCategoryEntity.get());
    }
}
