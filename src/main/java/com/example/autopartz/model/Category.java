package com.example.autopartz.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class Category {
    @Id
    Integer ID_category;
    @Column(name = "category_name")
    String cname;
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
