package com.example.autopartz.model.views;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.Immutable;

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
    Long partid;
    String partname;
    String cartype;
    String category;
    String pmname;
}
