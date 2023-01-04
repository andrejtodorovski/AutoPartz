package com.example.autopartz.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
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
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(Role.ROLE_WAREHOUSEMAN);
    }
}
