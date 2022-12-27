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
