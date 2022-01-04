package com.musinsa.category.domain.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
public class CategoryResponse {
    private Long id;
    private String categoryName;
    private List<CategoryResponse> childCategoryResponses = new ArrayList<>();
    private Boolean isDeleted;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
