package com.example.autopartz.model.manytomany;

import lombok.Data;

import java.io.Serializable;

@Data
public class RsForCmId implements Serializable {
    Integer rsid;
    Integer cmid;
}
