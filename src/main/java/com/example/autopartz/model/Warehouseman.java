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
    public static final LocalDate defaultEmployedFrom = LocalDate.of(2020,1,1);
    @ManyToOne
    @JoinColumn(name = "id_warehouse")
    Warehouse warehouse;

    public Warehouseman(String username, String name, String email, String password, String number, Warehouse warehouse) {
        super(username, name, email, password, number);
        this.employed_from=defaultEmployedFrom;
        this.warehouse= warehouse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Warehouseman that = (Warehouseman) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(Objects.equals(employed_from, defaultEmployedFrom))
            return Collections.singletonList(Role.ROLE_PENDING_WAREHOUSEMAN);
        else
            return Collections.singletonList(Role.ROLE_WAREHOUSEMAN);
    }
}
