package com.example.autopartz.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "car")
public class Car {
    @Id
    Long ID_car;
    Integer in_production_since;
    Integer in_production_till;
    String car_type;
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
