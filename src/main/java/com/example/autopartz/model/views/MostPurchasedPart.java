package com.example.autopartz.model.views;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Immutable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Immutable
@Table(name = "`most_purchased_part`")
public class MostPurchasedPart implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    String pname;
    String cmname;
    Long maxkol;
}
