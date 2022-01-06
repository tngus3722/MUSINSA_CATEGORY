package com.musinsa.category.service.category;

import com.musinsa.category.domain.category.CategoryRequest;
import com.musinsa.category.domain.category.CategoryResponse;

import java.util.List;

public interface CategoryService {

    List<CategoryResponse> getCategory();

    CategoryResponse getCategoryById(Long categoryId);

    CategoryResponse postCategory(CategoryRequest categoryRequest);

    CategoryResponse updateCategory(Long categoryId, CategoryRequest categoryRequest);

    void deleteCategory(Long categoryId);
}
