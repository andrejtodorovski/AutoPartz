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
@Table(name = "car_sample")
public class CarSample {
    @Id
    Integer vin;
    Integer year_of_production;
    Integer engine_power;
    Integer displacement;
    String fuel_type;
    Integer km_driven;
    @ManyToOne
    @JoinColumn(name = "id_user")
    Client client;
    @ManyToOne
    @JoinColumn(name = "id_car")
    Car car;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CarSample carSample = (CarSample) o;
        return vin != null && Objects.equals(vin, carSample.vin);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
