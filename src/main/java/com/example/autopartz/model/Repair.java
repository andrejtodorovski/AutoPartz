package com.example.autopartz.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class Repair {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_repair")
    Integer id;
    @OneToOne
    @JoinColumn(name = "id_order")
    Order order;
    @ManyToOne
    @JoinColumn(name = "id_repair_shop")
    RepairShop repairShop;
    @ManyToOne
    @JoinColumn(name = "id_service_book")
    ServiceBook serviceBook;

    public Repair(Order order, RepairShop repairShop, ServiceBook serviceBook) {
        this.order = order;
        this.repairShop = repairShop;
        this.serviceBook = serviceBook;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Repair repair = (Repair) o;
        return id != null && Objects.equals(id, repair.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
