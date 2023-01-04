package com.example.autopartz.model.views;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Immutable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Immutable
@Table(name = "`parts_for_car_type_and_category`")
public class PartsForCarTypeAndCategory implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    Integer partid;
    String partname;
    String cartype;
    String category;
    String pmname;
}
