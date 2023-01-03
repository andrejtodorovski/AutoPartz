package com.example.autopartz.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class Part {
    @Id
    Long ID_part;
    String part_name;
    String part_description;
    @ManyToOne
    @JoinColumn(name = "id_part_manufacturer")
    PartManufacturer partManufacturer;
    @ManyToMany
    @JoinTable(name = "part_is_from_category", joinColumns =
    @JoinColumn(name = "id_part"),
    inverseJoinColumns = @JoinColumn(name = "id_category"))
    @ToString.Exclude
    List<Category> categoryList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Part part = (Part) o;
        return ID_part != null && Objects.equals(ID_part, part.ID_part);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
