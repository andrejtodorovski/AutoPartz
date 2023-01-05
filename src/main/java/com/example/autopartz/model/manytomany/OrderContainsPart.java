package com.example.autopartz.model.manytomany;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "`order_contains_part`")
@IdClass(OrderContainsPartId.class)
public class OrderContainsPart {
    @Id
    @Column(name = "id_part")
    Integer partid;
    @Column(name = "id_order")
    @Id
    Integer orderid;
    Integer quantity_order;

    public OrderContainsPart(Integer partid, Integer orderid, Integer quantity_order) {
        this.partid = partid;
        this.orderid = orderid;
        this.quantity_order = quantity_order;
    }
}
