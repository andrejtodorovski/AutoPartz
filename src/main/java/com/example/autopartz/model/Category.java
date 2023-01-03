package com.example.autopartz.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class Category {
    @Id
    Long ID_category;
    String category_name;
    @ManyToOne
    @JoinColumn(name = "id_parent_category")
    Category ID_parent_category;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Category category = (Category) o;
        return ID_category != null && Objects.equals(ID_category, category.ID_category);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}