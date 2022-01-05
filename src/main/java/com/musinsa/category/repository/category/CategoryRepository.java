package com.musinsa.category.repository.category;

import com.musinsa.category.entity.category.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    @Query("SELECT c FROM CategoryEntity c WHERE c.parentCategoryEntity is null")
    List<CategoryEntity> findRootCategory();
}
