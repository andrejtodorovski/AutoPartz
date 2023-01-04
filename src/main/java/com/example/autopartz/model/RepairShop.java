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
@Table(name = "repair_shop")
public class RepairShop {
    @Id
    @Column(name = "ID_repair_shop")
    Integer id;
    @Column(name = "rs_name")
    String name;
    @Column(name = "rs_location")
    String location;
    @Column(name = "rs_phone_number")
    String number;
    @ManyToMany
    @JoinTable(name = "repair_shop_is_authorized_for_car_make", joinColumns =
    @JoinColumn(name = "id_repair_shop"),
            inverseJoinColumns = @JoinColumn(name = "id_car_manufacturer"))
    @ToString.Exclude
    List<CarManufacturer> carManufacturerList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        RepairShop that = (RepairShop) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
