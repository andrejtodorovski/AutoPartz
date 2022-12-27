package com.example.autopartz.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

// извештај за сите сервиси и за сите број на reviews и avg рејтинг
@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Immutable
@Table(name = "repair_shop_reviews_summary")
@Subselect("select * from repair_shop_reviews_summary")
public class RepairShopReviewsSummary implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    Long rsid;
    String rsname;
    Integer reviewcount;
    Float reviewaverage;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        RepairShopReviewsSummary that = (RepairShopReviewsSummary) o;
        return rsid != null && Objects.equals(rsid, that.rsid);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
