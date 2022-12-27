package com.example.autopartz.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class Repair {
    @Id
    Long ID_repair;
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
