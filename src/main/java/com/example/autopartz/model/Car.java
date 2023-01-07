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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_car")
    Integer id;
    Integer in_production_since;
    Integer in_production_till;
    @Column(name = "car_type")
    String cartype;
    @ManyToOne
    @JoinColumn(name = "id_car_manufacturer")
    CarManufacturer car_manufacturer;

    public Car(Integer in_production_since, Integer in_production_till, String cartype, CarManufacturer car_manufacturer) {
        this.in_production_since = in_production_since;
        this.in_production_till = in_production_till;
        this.cartype = cartype;
        this.car_manufacturer = car_manufacturer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Car car = (Car) o;
        return id != null && Objects.equals(id, car.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
