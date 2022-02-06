package com.musinsa.category.controller.category;

import com.musinsa.category.annotation.ValidationGroup;
import com.musinsa.category.dto.category.CategoryRequest;
import com.musinsa.category.dto.category.CategoryResponse;
import com.musinsa.category.service.category.CategoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CategoryController {

    private final CategoryService categoryService;


    @GetMapping("/categories")
    public List<CategoryResponse> getCategory() {
        return categoryService.getCategory();
    }

    @GetMapping("/category/{category_id}")
    public CategoryResponse getCategoryById(@PathVariable(value = "category_id") Long categoryId) throws Exception {
        return categoryService.getCategoryById(categoryId);
    }

    @PostMapping("/category")
    public CategoryResponse postCategory(@RequestBody @Validated(ValidationGroup.Create.class) CategoryRequest categoryRequest) {
        return categoryService.postCategory(categoryRequest);
    }

    @DeleteMapping("/category/{category_id}")
    public void deleteCategory(@PathVariable(value = "category_id") Long categoryId) {
        categoryService.deleteCategory(categoryId);
    }

    @PutMapping("/category/{category_id}")
    public CategoryResponse updateCategory(@PathVariable(value = "category_id") Long categoryId,
        @RequestBody @Validated(ValidationGroup.Update.class) CategoryRequest categoryRequest) {
        return categoryService.updateCategory(categoryId, categoryRequest);
    }
}
