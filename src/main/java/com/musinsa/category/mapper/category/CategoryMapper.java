package com.musinsa.category.mapper.category;

import com.musinsa.category.domain.response.CategoryResponse;
import com.musinsa.category.entity.category.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    @Mapping(source = "childCategoryEntities", target = "childCategoryResponses")
    CategoryResponse toCategoryResponse(CategoryEntity categoryEntity);
}
