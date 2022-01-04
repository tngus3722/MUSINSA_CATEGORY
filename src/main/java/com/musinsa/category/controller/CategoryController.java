package com.musinsa.category.controller;

import com.musinsa.category.domain.response.CategoryResponse;
import com.musinsa.category.service.category.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @GetMapping("/category")
    public ResponseEntity<List<CategoryResponse>> getCategory() {
        return new ResponseEntity<List<CategoryResponse>>(categoryService.getCategory(), HttpStatus.OK);
    }

    @GetMapping("/category/{category_id}")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable(value = "category_id", required = true) Long categoryId) throws Exception {
        return new ResponseEntity<CategoryResponse>(categoryService.getCategoryById(categoryId), HttpStatus.OK);
    }
}
