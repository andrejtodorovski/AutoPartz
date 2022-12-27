package com.example.autopartz.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@ToString
@Entity
public class Administrator extends User{
    LocalDate authorized_from;
    LocalDate authorized_till;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Administrator that = (Administrator) o;
        return ID_user != null && Objects.equals(ID_user, that.ID_user);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
