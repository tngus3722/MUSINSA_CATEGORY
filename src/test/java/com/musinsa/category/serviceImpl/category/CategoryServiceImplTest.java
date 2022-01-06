package com.musinsa.category.serviceImpl.category;

import com.musinsa.category.domain.category.CategoryResponse;
import com.musinsa.category.entity.category.CategoryEntity;
import com.musinsa.category.mapper.category.CategoryMapper;
import com.musinsa.category.repository.category.CategoryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
public class CategoryServiceImplTest {

    @MockBean
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
        List<CategoryResponse> response = categoryRepository.findRootCategory().stream().map(CategoryMapper.INSTANCE::toCategoryResponse).collect(Collectors.toList());
        // then
        assertEquals(response.get(0).getCategoryName(), "hello");
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
        CategoryResponse categoryResponse = CategoryMapper.INSTANCE.toCategoryResponse(categoryRepository.findById(1L).get());
        // then
        assertEquals(categoryResponse.getCategoryName(), "hello");
    }

    @Test
    public void postCategory() {
        // given
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(1L);
        categoryEntity.setCategoryName("hello");
        // mocking
        given(categoryRepository.save(any(CategoryEntity.class))).willReturn(categoryEntity);
        // when
        CategoryResponse categoryResponse = CategoryMapper.INSTANCE.toCategoryResponse(categoryRepository.save(categoryEntity));
        // then
        assertEquals(categoryResponse.getCategoryName(), "hello");
    }

    @Test
    public void updateCategory() {
        // given
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(1L);
        categoryEntity.setCategoryName("before");
        //when
        categoryEntity.setCategoryName("after");
        // mocking
        CategoryResponse categoryResponse = CategoryMapper.INSTANCE.toCategoryResponse(categoryEntity);
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
        assertEquals(categoryRepository.findById(1L).get().getChildCategoryEntities().isEmpty(), false);
        assertEquals(categoryRepository.findById(1L).get().getChildCategoryEntities().size() == 1, true);
    }
}