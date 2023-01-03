package com.example.autopartz.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

// извештај за сите сервиси и за сите број на reviews и avg рејтинг
@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Immutable
@Table(name = "`reviews_for_user`")
public class ReviewsForUser implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    Long repairid;
    Long userid;
    Integer rating;
    String rcomment;
    String rsname;
    String rslocation;
}


