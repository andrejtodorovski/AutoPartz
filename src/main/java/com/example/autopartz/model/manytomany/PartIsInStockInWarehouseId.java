package com.example.autopartz.model.manytomany;

import lombok.Data;

import java.io.Serializable;

@Data
public class PartIsInStockInWarehouseId implements Serializable {
    Integer partid;
    Integer warehouseid;
}
