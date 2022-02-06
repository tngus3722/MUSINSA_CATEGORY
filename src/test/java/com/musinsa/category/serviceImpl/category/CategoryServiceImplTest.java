package com.musinsa.category.serviceImpl.category;

import com.musinsa.category.dto.category.CategoryRequest;
import com.musinsa.category.dto.category.CategoryResponse;
import com.musinsa.category.entity.category.CategoryEntity;
import com.musinsa.category.exception.staticException.RequestInputException;
import com.musinsa.category.repository.category.CategoryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceImplTest {

    @InjectMocks
    private CategoryServiceImpl categoryService;
    @Mock
    private CategoryRepository categoryRepository;

    @Test
    public void getCategoryService() {
        // given
        List<CategoryEntity> categoryEntities = new ArrayList<>();
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setCategoryName("hello");
        categoryEntities.add(categoryEntity);
        // mocking
        given(categoryRepository.findRootCategory()).willReturn(categoryEntities);
        // when
        List<CategoryResponse> categoryResponses = categoryService.getCategory();
        // then
        assertEquals(categoryResponses.get(0).getCategoryName(), "hello");
    }

    @Test
    public void getCategoryByIdService() {
        // given
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setCategoryName("hello");
        Optional<CategoryEntity> optionalCategoryEntity = Optional.of(categoryEntity);
        // mocking
        given(categoryRepository.findById(any(Long.class))).willReturn(optionalCategoryEntity);
        // when
        CategoryResponse categoryResponse = categoryService.getCategoryById(3L);
        // then
        assertEquals(categoryResponse.getCategoryName(), "hello");
    }

    @Test
    public void postCategory() {
        // given
        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setCategoryName("hello");
        CategoryEntity categoryEntity = new CategoryEntity(categoryRequest);
        categoryEntity.setId(1L);

        // mocking
        given(categoryRepository.save(any(CategoryEntity.class))).willReturn(categoryEntity);
        // when
        CategoryResponse categoryResponse = categoryService.postCategory(categoryRequest);
        // then
        assertEquals(categoryResponse.getCategoryName(), "hello");
    }

    @Test
    public void updateCategory() {
        // given
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setCategoryName("before");
        categoryEntity.setId(1L);
        Optional<CategoryEntity> optionalCategoryEntity = Optional.of(categoryEntity);

        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setCategoryName("after");
        // mocking
        given(categoryRepository.findById(any(Long.class))).willReturn(optionalCategoryEntity);
        //when
        CategoryResponse categoryResponse = categoryService.updateCategory(1L, categoryRequest);
        // then
        assertEquals(categoryResponse.getCategoryName(), "after");
    }

    @Test
    public void deleteCategory() {
        //given
        CategoryEntity parentCategoryEntity = new CategoryEntity();
        parentCategoryEntity.setId(1L);
        parentCategoryEntity.setCategoryName("parent");

        CategoryEntity childCategoryEntity = new CategoryEntity();
        childCategoryEntity.setId(2L);
        childCategoryEntity.setParentCategoryEntity(parentCategoryEntity);
        parentCategoryEntity.getChildCategoryEntities().add(childCategoryEntity);
        childCategoryEntity.setCategoryName("child");

        Optional<CategoryEntity> optionalCategoryEntity = Optional.of(parentCategoryEntity);

        // mocking
        given(categoryRepository.findById(any(Long.class))).willReturn(optionalCategoryEntity);

        // when, then
        try {
            categoryService.deleteCategory(2L);
        }catch (RequestInputException e){
            assertEquals(e.getCode().longValue(), 4);
        }
        assertEquals(categoryRepository.findById(1L).get().getChildCategoryEntities().isEmpty(), false);
        assertEquals(categoryRepository.findById(1L).get().getChildCategoryEntities().size() == 1, true);
    }
}