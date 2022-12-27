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
public class Review {
    @Id
    Long ID_review;
    Integer review_rating;
    String review_comment;
    @ManyToOne
    @JoinColumn(name = "id_repair")
    Repair repair;
    @ManyToOne
    @JoinColumn(name = "id_user")
    Client client;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Review review = (Review) o;
        return ID_review != null && Objects.equals(ID_review, review.ID_review);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
