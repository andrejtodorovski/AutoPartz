package com.example.autopartz.model.views;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class OrdersForUserId implements Serializable {
    Integer userid;
    LocalDateTime orderdate;
    String partname;
}
