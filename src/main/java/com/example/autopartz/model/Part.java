package com.example.autopartz.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class Part {
    @Id
    @Column(name = "ID_part")
    Integer id;
    @Column(name = "part_name")
    String name;
    @Column(name = "part_description")
    String description;
    @ManyToOne
    @JoinColumn(name = "id_part_manufacturer")
    PartManufacturer manufacturer;
    @ManyToMany
    @JoinTable(name = "part_is_from_category", joinColumns =
    @JoinColumn(name = "id_part"),
    inverseJoinColumns = @JoinColumn(name = "id_category"))
    @ToString.Exclude
    List<Category> categoryList;
    @ManyToMany
    @JoinTable(name = "part_is_in_stock_in_warehouse", joinColumns =
    @JoinColumn(name = "id_part"),
            inverseJoinColumns = @JoinColumn(name = "id_warehouse"))
    @ToString.Exclude
    List<Warehouse> warehouseList;
    @ManyToMany
    @JoinTable(name = "part_is_appropriate_for_car", joinColumns =
    @JoinColumn(name = "id_part"),
            inverseJoinColumns = @JoinColumn(name = "id_car"))
    @ToString.Exclude
    List<Car> carList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Part part = (Part) o;
        return id != null && Objects.equals(id, part.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
