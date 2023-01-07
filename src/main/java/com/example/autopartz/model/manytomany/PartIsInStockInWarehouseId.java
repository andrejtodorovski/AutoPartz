package com.example.autopartz.model.manytomany;

import lombok.Data;

import java.io.Serializable;

@Data
public class PartIsInStockInWarehouseId implements Serializable {
    Integer partid;
    Integer warehouseid;

    public PartIsInStockInWarehouseId(Integer pId, Integer whId) {
        this.partid = pId;
        this.warehouseid = whId;
    }

    public PartIsInStockInWarehouseId() {
    }
}
