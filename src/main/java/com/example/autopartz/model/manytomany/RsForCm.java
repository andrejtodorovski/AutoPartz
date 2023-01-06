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
@Table(name = "`repair_shop_is_authorized_for_car_make`")
@IdClass(RsForCmId.class)
public class RsForCm {
    @Id
    @Column(name = "id_repair_shop")
    Integer rsid;
    @Column(name = "id_car_manufacturer")
    @Id
    Integer cmid;
}