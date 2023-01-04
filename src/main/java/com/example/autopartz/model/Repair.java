package com.example.autopartz.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class Repair {
    @Id
    Integer ID_repair;
    @ManyToOne
    @JoinColumn(name = "vin")
    CarSample carSample;
    @ManyToOne
    @JoinColumn(name = "id_repair_shop")
    RepairShop repairShop;
    @ManyToOne
    @JoinColumn(name = "id_service_book")
    ServiceBook serviceBook;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Repair repair = (Repair) o;
        return ID_repair != null && Objects.equals(ID_repair, repair.ID_repair);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
