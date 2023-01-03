package com.example.autopartz.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "part_manufacturer")
public class PartManufacturer {
    @Id
    Long ID_part_manufacturer;
    @Column(name = "pm_name")
    String name;
    String pm_location;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PartManufacturer that = (PartManufacturer) o;
        return ID_part_manufacturer != null && Objects.equals(ID_part_manufacturer, that.ID_part_manufacturer);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
