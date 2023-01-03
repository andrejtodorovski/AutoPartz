package com.example.autopartz.model.views;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Immutable;

import java.io.Serial;
import java.io.Serializable;

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


