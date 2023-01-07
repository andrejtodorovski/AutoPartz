package com.example.autopartz.model.manytomany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "`part_is_appropriate_for_car`")
@IdClass(PartIsAppropriateForCarId.class)
public class PartIsAppropriateForCar {
    @Id
    @Column(name = "id_part")
    Integer partid;
    @Column(name = "id_car")
    @Id
    Integer carid;

    public PartIsAppropriateForCar(Integer partid, Integer carid) {
        this.partid = partid;
        this.carid = carid;
    }
}
