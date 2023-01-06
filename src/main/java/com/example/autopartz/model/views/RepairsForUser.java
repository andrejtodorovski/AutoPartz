package com.example.autopartz.model.views;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Immutable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
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
public class RepairsForUser implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    Integer repairid;
    Integer userid;
    LocalDateTime orderdate;
    Integer orderid;
    String rsname;
}


