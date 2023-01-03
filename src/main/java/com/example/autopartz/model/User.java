package com.example.autopartz.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "users_table")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    Long ID_user;
    String username;
    String email;
    String name_user;
    @Column(name = "password_user")
    String password;
    LocalDateTime user_created_on;
    String phone_number;
    @ManyToOne
    @JoinColumn(name = "id_administrator")
    Administrator administrator;

    public User(String username, String name, String email, String password, String number) {
        this.username = username;
        this.name_user = name;
        this.email = email;
        this.password = password;
        this.phone_number = number;
        this.user_created_on = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return ID_user != null && Objects.equals(ID_user, user.ID_user);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
