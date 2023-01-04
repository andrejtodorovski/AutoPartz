package com.example.autopartz.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "car")
public class Car {
    @Id
    Integer ID_car;
    Integer in_production_since;
    Integer in_production_till;
    @Column(name = "car_type")
    String cartype;
    @ManyToOne
    @JoinColumn(name = "id_car_manufacturer")
    CarManufacturer car_manufacturer;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Car car = (Car) o;
        return ID_car != null && Objects.equals(ID_car, car.ID_car);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
