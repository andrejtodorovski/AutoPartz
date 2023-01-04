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
public class Review {
    @Id
    Integer ID_review;
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
