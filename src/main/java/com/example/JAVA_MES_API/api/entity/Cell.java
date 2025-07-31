package com.example.JAVA_MES_API.api.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "CELL")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Cell {

	@Id
	@Column(name = "CELL_CD", length = 10)
	private String cellCd;

	@Column(name = "ZONE_CD", length = 10)
	private String zoneCd;

	@Column(name = "CELL_NM", length = 50)
	private String cellNm;

	@Column(name = "CELL_REMARK", length = 200)
	private String cellRemark;
}


