package com.musinsa.category.controller;

import com.musinsa.category.annotation.ValidationGroup;
import com.musinsa.category.domain.category.CategoryRequest;
import com.musinsa.category.domain.category.CategoryResponse;
import com.musinsa.category.service.category.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable(value = "category_id") Long categoryId) throws Exception {
        return new ResponseEntity<CategoryResponse>(categoryService.getCategoryById(categoryId), HttpStatus.OK);
    }

    @PostMapping("/category")
    public ResponseEntity<Void> postCategory(@RequestBody @Validated(ValidationGroup.Create.class) CategoryRequest categoryRequest) {
        categoryService.postCategory(categoryRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/category/{category_id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable(value = "category_id") Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/category/{category_id}")
    public ResponseEntity<Void> updateCategory(@PathVariable(value = "category_id") Long categoryId,
                                               @RequestBody @Validated(ValidationGroup.Update.class) CategoryRequest categoryRequest) {
        categoryService.updateCategory(categoryId, categoryRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
