package com.example.autopartz.model.manytomany;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
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

    public PartIsInStockInWarehouse(Integer partid, Integer warehouseid, Integer quantity) {
        this.partid = partid;
        this.warehouseid = warehouseid;
        this.quantity = quantity;
    }
}
