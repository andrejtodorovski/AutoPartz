package com.example.autopartz.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer ID_price;
    Integer amount;
    LocalDate price_from;
    LocalDate price_till;
    @ManyToOne
    @JoinColumn(name = "id_part")
    Part part;

    public Price(Integer amount, LocalDate price_from, Part part) {
        this.amount = amount;
        this.price_from = price_from;
        this.part = part;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Price price = (Price) o;
        return ID_price != null && Objects.equals(ID_price, price.ID_price);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
