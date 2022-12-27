package com.example.autopartz.model;

import jakarta.persistence.Entity;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class Client extends User{
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Client client = (Client) o;
        return ID_user != null && Objects.equals(ID_user, client.ID_user);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
