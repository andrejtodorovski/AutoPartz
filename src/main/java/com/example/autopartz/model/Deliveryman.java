package com.example.autopartz.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.Hibernate;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "delivery_man")
public class Deliveryman extends User{
    LocalDate employed_from;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Deliveryman that = (Deliveryman) o;
        return ID_user != null && Objects.equals(ID_user, that.ID_user);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
