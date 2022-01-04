package com.musinsa.category.service.category;

import com.musinsa.category.domain.response.CategoryResponse;

import java.util.List;

public interface CategoryService {

    List<CategoryResponse> getCategory();

    CategoryResponse getCategoryById(Long categoryId) throws Exception;
}
