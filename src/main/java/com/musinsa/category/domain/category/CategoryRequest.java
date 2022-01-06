package com.musinsa.category.domain.category;

import com.musinsa.category.annotation.ValidationGroup;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Setter
@Getter
public class CategoryRequest {
    @NotNull(groups = {ValidationGroup.Create.class, ValidationGroup.Update.class}, message = "category name is required ")
    @Size(min = 1 , groups = {ValidationGroup.Create.class, ValidationGroup.Update.class}, message = "category name length 1 or more")
    private String categoryName;
    @Min(value = 1, groups = {ValidationGroup.Create.class} , message = "must be 1 or more")
    @Null(groups = {ValidationGroup.Update.class} , message = "parent category id null only when update")
    private Long parentCategoryId;
}
