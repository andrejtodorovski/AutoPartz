package com.example.autopartz.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.hibernate.Hibernate;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class Warehouseman extends User{
    LocalDate employed_from;
    @ManyToOne
    @JoinColumn(name = "id_warehouse")
    Warehouse warehouse;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Warehouseman that = (Warehouseman) o;
        return ID_user != null && Objects.equals(ID_user, that.ID_user);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
