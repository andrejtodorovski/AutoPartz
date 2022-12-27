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
@Table(name = "car_manufacturer", schema = "project")
public class CarManufacturer {
    @Id
    Long ID_car_manufacturer;
    String cm_name;
    String cm_country;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CarManufacturer that = (CarManufacturer) o;
        return ID_car_manufacturer != null && Objects.equals(ID_car_manufacturer, that.ID_car_manufacturer);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
