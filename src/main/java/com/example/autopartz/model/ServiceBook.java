package com.example.autopartz.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "service_book")
public class ServiceBook {
    @Id
    @Column(name = "ID_service_book")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    LocalDateTime sb_created_on;
    @OneToOne
    @JoinColumn(name = "vin")
    CarSample carSample;

    public ServiceBook(CarSample carSample) {
        this.sb_created_on = LocalDateTime.now();
        this.carSample = carSample;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ServiceBook that = (ServiceBook) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
