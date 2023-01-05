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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_order")
    Integer orderid;
    String order_status;
    @Column(name = "order_date")
    LocalDateTime date;
    @JoinColumn(name = "id_user")
    @ManyToOne
    Client user;
    @ManyToMany
    @JoinTable(name = "order_contains_part", joinColumns =
    @JoinColumn(name = "id_order"),
            inverseJoinColumns = @JoinColumn(name = "id_part"))
    @ToString.Exclude
    List<Part> partList;

    public Order(Client user) {
        this.order_status = "created";
        this.user = user;
        this.date = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Order order = (Order) o;
        return orderid != null && Objects.equals(orderid, order.orderid);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
