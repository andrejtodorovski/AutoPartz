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
    Integer ID_service_book;
    LocalDateTime sb_created_on;
    @OneToOne
    @JoinColumn(name = "vin")
    CarSample carSample;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ServiceBook that = (ServiceBook) o;
        return ID_service_book != null && Objects.equals(ID_service_book, that.ID_service_book);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
