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
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Immutable
@Table(name = "`deliveries_in_progress`")
public class DeliveriesInProgress implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    Integer deliveryid;
    Integer userid;
    Integer orderid;
    LocalDateTime datum;
    String status;
    String address;
}
