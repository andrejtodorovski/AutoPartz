package com.example.autopartz.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class Client extends User{
    public Client(String username, String name, String email, String password, String number) {
        super(username, name, email, password, number);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Client client = (Client) o;
        return id != null && Objects.equals(id, client.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(Role.ROLE_CLIENT);
    }
}
