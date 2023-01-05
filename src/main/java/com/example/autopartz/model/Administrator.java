package com.example.autopartz.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
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
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(Role.ROLE_ADMIN);
    }
}
