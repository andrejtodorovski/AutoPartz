package com.example.autopartz.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class OrdersForUserId implements Serializable {
    Long userid;
    LocalDateTime orderdate;
    String partname;
}
