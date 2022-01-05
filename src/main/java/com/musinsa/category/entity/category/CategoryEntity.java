package com.musinsa.category.entity.category;

import com.musinsa.category.domain.category.CategoryRequest;
import com.musinsa.category.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Setter
@Getter
@SQLDelete(sql = "UPDATE category SET is_deleted = true WHERE id=?")
@Where(clause = "is_deleted = false")
@Table(name = "category")
public class CategoryEntity extends BaseEntity {
    @Basic
    @Column(name = "category_name")
    private String categoryName;
    @JoinColumn(name = "parent_category_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private CategoryEntity parentCategoryEntity = null;
    @OrderBy("id asc")
    @OneToMany(mappedBy = "parentCategoryEntity")
    private List<CategoryEntity> childCategoryEntities = new ArrayList<>();

    public CategoryEntity(CategoryRequest categoryRequest) {
        this.update(categoryRequest);
    }

    public void update(CategoryRequest categoryRequest) {
        this.categoryName = categoryRequest.getCategoryName();
    }

}
