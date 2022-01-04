package com.musinsa.category.controller;

import com.musinsa.category.domain.response.CategoryResponse;
import com.musinsa.category.service.category.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @GetMapping("/test")
    public ResponseEntity<List<CategoryResponse>> test() {
        return new ResponseEntity<List<CategoryResponse>>(categoryService.getCategoryList(), HttpStatus.OK);
    }
}
