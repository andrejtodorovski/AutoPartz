package com.example.autopartz.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "order_table")
public class Order {
    @Id
    Long ID_order;
    String order_status;
    LocalDateTime order_date;
    @JoinColumn(name = "id_user")
    @ManyToOne
    Client user;

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
