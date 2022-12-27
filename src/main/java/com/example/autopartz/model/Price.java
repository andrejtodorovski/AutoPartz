package com.example.autopartz.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.hibernate.Hibernate;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Price {
    @Id
    Long ID_price;
    Integer amount;
    LocalDate price_from;
    LocalDate price_till;
    @ManyToOne
    @JoinColumn(name = "id_part")
    Part part;

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
