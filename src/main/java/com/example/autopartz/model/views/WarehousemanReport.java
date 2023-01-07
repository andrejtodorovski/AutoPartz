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

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Immutable
@Table(name = "`warehouseman_report`")
@IdClass(WarehousemanReportId.class)
public class WarehousemanReport implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    Integer wid;
    @Id
    String pname;
    Long quantityordered;
    Integer quantitywarehouse;
    Long quantitylast3months;
    String hasenoughinstock;
}
