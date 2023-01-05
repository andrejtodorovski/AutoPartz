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
@Table(name = "`part_is_in_stock_in_warehouse`")
@IdClass(PartIsInStockInWarehouseId.class)
public class PartIsInStockInWarehouse {
    @Id
    @Column(name = "id_part")
    Integer partid;
    @Column(name = "id_warehouse")
    @Id
    Integer warehouseid;
    @Column(name = "quantity_warehouse")
    Integer quantity;
}
