package com.example.autopartz.model.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
public class CurrentOrderDTO {
    String partName;
    String manufacturerName;
    Integer quantity;
    Integer price;

    public CurrentOrderDTO(String partName, String manufacturerName, Integer quantity, Integer price) {
        this.partName = partName;
        this.manufacturerName = manufacturerName;
        this.quantity = quantity;
        this.price = price;
    }
}
