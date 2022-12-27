package com.example.autopartz.model;

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
@Table(name = "repair_shop")
public class RepairShop {
    @Id
    Long ID_repair_shop;
    String rs_name;
    String rs_location;
    String rs_phone_number;

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
