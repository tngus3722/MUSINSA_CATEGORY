package com.musinsa.category.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

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
    @CreationTimestamp
    @Column(name = "created_at", insertable = false, updatable = false)
    protected Timestamp createdAt;
    @Basic
    @UpdateTimestamp
    @Column(name = "updated_at", insertable = false, updatable = false)
    protected Timestamp updatedAt;
}
