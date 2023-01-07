package com.example.autopartz.model.manytomany;

import lombok.Data;

import java.io.Serializable;

@Data
public class PartIsFromCategoryId implements Serializable {
    Integer partid;
    Integer categoryid;
}
