package com.example.autopartz.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

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
    Long ID_repair_shop;
    String rs_name;
    String rs_location;
    String rs_phone_number;
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
        return ID_repair_shop != null && Objects.equals(ID_repair_shop, that.ID_repair_shop);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
