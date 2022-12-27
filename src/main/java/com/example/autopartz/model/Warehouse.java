package com.example.autopartz.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class Warehouse {
    @Id
    Long ID_warehouse;
    String warehouse_location;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Warehouse warehouse = (Warehouse) o;
        return ID_warehouse != null && Objects.equals(ID_warehouse, warehouse.ID_warehouse);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
