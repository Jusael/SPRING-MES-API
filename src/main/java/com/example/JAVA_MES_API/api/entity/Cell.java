package com.example.JAVA_MES_API.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cell")
@Getter
@Setter
public class Cell {

    @Id
    @Column(name = "CELL_CD", length = 10)
    private String cellCd;

    @Column(name = "CELL_NM", length = 50)
    private String cellNm;

    @Column(name = "ZONE_CD", length = 10)
    private String zoneCd;

    @Column(name = "ZONE_NM", length = 50)
    private String zoneNm;

    @Column(name = "WARE_HOUSE_CD", length = 10)
    private String wareHouseCd;

    @Column(name = "WARE_HOUSE_NM", length = 50)
    private String wareHouseNm;

    @Column(name = "CELL_REMARK", length = 200)
    private String cellRemark;
}
