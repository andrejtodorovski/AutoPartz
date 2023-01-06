package com.example.autopartz.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer ID_delivery;
    @Column(name = "delivery_status")
    String status;
    @Column(name = "delivery_address")
    String address;
    @ManyToOne
    @JoinColumn(name = "id_user")
    Deliveryman deliveryman;
    @JoinColumn(name = "id_order")
    @OneToOne
    Order order;

    public Delivery(String status, String address, Deliveryman deliveryman, Order order) {
        this.status = status;
        this.address = address;
        this.deliveryman = deliveryman;
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Delivery delivery = (Delivery) o;
        return ID_delivery != null && Objects.equals(ID_delivery, delivery.ID_delivery);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
