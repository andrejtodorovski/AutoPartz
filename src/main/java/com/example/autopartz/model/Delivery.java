package com.example.autopartz.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Delivery {
    @Id
    Long ID_delivery;
    String delivery_status;
    String delivery_address;
    @ManyToOne
    @JoinColumn(name = "id_user")
    Deliveryman deliveryman;
    @JoinColumn(name = "id_order")
    @OneToOne
    Order order;

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
