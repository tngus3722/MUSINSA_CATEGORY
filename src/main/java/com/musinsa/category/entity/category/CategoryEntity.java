package com.musinsa.category.entity.category;

import com.musinsa.category.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Setter
@Getter
@Table(name = "category")
public class CategoryEntity extends BaseEntity {
    @Basic
    @Column(name = "category_name")
    private String categoryName;
    @JoinColumn(name = "parent_category_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CategoryEntity parentCategory;
    @OneToMany(mappedBy = "parentCategory")
    private List<CategoryEntity> childCategoryEntities;
}
