package com.musinsa.category.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.musinsa.category.domain.category.CategoryRequest;
import com.musinsa.category.domain.category.CategoryResponse;
import com.musinsa.category.service.category.CategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CategoryController.class)
public class CategoryControllerTest {

    @Resource
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    @Test
    public void getCategoryById() throws Exception {
        // given
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setCategoryName("카테고리네임");

        //given
        given(categoryService.getCategoryById(1L)).willReturn(categoryResponse);

        // then
        mockMvc.perform(get("/category/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categoryName", is("카테고리네임")));
    }

    @Test
    public void getCategory() throws Exception {
        // given
        List<CategoryResponse> categoryResponses = new ArrayList<>();
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setCategoryName("카테고리네임");
        categoryResponses.add(categoryResponse);

        //given
        given(categoryService.getCategory()).willReturn(categoryResponses);

        // then
        mockMvc.perform(get("/category"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].categoryName", is("카테고리네임")));
    }

    @Test
    public void postCategory() throws Exception {
        // given
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setCategoryName("test");

        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setCategoryName("test");
        ObjectMapper objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(categoryRequest);

        //given
        given(categoryService.postCategory(any(CategoryRequest.class))).willReturn(categoryResponse);

        // then
        mockMvc.perform(post("/category").content(content).contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.categoryName", is("test")))
                .andExpect(status().isCreated());
    }

    @Test
    public void deleteCategory() throws Exception {
        mockMvc.perform(delete("/category/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void putCategory() throws Exception {
        // given
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setCategoryName("test2");

        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setCategoryName("test2");
        ObjectMapper objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(categoryRequest);

        //given
        given(categoryService.updateCategory(any(Long.class), any(CategoryRequest.class))).willReturn(categoryResponse);

        // then
        mockMvc.perform(put("/category/1").content(content).contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.categoryName", is("test2")))
                .andExpect(status().isOk());
    }
}