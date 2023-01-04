package com.example.autopartz.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "car_manufacturer", schema = "project")
public class CarManufacturer {
    @Id
    Integer ID_car_manufacturer;
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
