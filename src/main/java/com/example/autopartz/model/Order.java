package com.example.autopartz.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "order_table")
public class Order {
    @Id
    Integer ID_order;
    String order_status;
    LocalDateTime order_date;
    @JoinColumn(name = "id_user")
    @ManyToOne
    Client user;
    @ManyToMany
    @JoinTable(name = "order_contains_part", joinColumns =
    @JoinColumn(name = "id_order"),
            inverseJoinColumns = @JoinColumn(name = "id_part"))
    @ToString.Exclude
    List<Part> partList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Order order = (Order) o;
        return ID_order != null && Objects.equals(ID_order, order.ID_order);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
