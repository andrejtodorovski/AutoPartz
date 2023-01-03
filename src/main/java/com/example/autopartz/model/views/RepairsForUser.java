package com.example.autopartz.model.views;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Immutable;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

// извештај за сите сервиси и за сите број на reviews и avg рејтинг
@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Immutable
@Table(name = "`repairs_for_user`")
@IdClass(OrdersForUserId.class)
public class RepairsForUser implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    Long userid;
    @Id
    LocalDateTime orderdate;
    @Id
    String partname;
    Integer vin;
    String rsname;
}


