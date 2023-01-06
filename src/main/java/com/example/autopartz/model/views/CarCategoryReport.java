package com.example.autopartz.model.views;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Immutable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Immutable
@Table(name = "`car_category_report`")
@IdClass(CarCategoryReportId.class)
public class CarCategoryReport implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    String cname;
    @Id
    String cmname;
    Long partsordered;
}
