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
public class Part {
    @Id
    Long ID_part;
    String part_name;
    String part_description;
    @ManyToOne
    @JoinColumn(name = "id_part_manufacturer")
    PartManufacturer partManufacturer;

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
