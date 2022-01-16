package com.musinsa.category.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Setter
@Getter
@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Long id;
    @Basic
    @Column(name = "is_deleted")
    protected Boolean isDeleted = false;
    @Basic
    @CreatedDate
    @Column(name = "created_at", insertable = false, updatable = false)
    protected LocalDateTime createdAt;
    @Basic
    @LastModifiedDate
    @Column(name = "updated_at", insertable = false, updatable = false)
    protected LocalDateTime updatedAt;
}
