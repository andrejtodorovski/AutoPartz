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
@Table(name = "`part_is_from_category`")
@IdClass(PartIsFromCategoryId.class)
public class PartIsFromCategory {
    @Id
    @Column(name = "id_part")
    Integer partid;
    @Column(name = "id_category")
    @Id
    Integer categoryid;

    public PartIsFromCategory(Integer partid, Integer categoryid) {
        this.partid = partid;
        this.categoryid = categoryid;
    }
}
