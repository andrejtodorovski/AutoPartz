package com.example.autopartz.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "delivery_man")
public class Deliveryman extends User{
    LocalDate employed_from;
    public static final LocalDate defaultEmployedFrom = LocalDate.of(2020,1,1);

    public Deliveryman(String username, String name, String email, String password, String number) {
        super(username, name, email, password, number);
        this.employed_from = defaultEmployedFrom;
    }

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
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(Objects.equals(employed_from, defaultEmployedFrom))
            return Collections.singletonList(Role.ROLE_PENDING_DELIVERYMAN);
        else
            return Collections.singletonList(Role.ROLE_DELIVERYMAN);
    }
}
