package com.example.JAVA_MES_API.api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "receipt_pack")
@Getter
@Setter
public class ReceiptPack {

    @EmbeddedId
    private ReceiptPackId id;

    @Column(name = "WAREHOUSE_CD", length = 10)
    private String warehouseCd;

    @Column(name = "ZONE_CD", length = 10)
    private String zoneCd;

    @Column(name = "CELL_CD", length = 10)
    private String cellCd;

    @Column(name = "PALLET_CD", length = 10)
    private String palletCd;

    @Column(name = "RECEIPT_PACK_UNIT", length = 50)
    private String receiptPackUnit;

    @Column(name = "RECEIPT_PACK_BARCODE_NO", length = 100)
    private String receiptPackBarcodeNo;

    @Column(name = "RECEIPT_PACK_QTY", precision = 15, scale = 4)
    private BigDecimal receiptPackQty;

    @Column(name = "RECEIPT_PACK_REMAIN_QTY", precision = 15, scale = 4)
    private BigDecimal receiptPackRemainQty;

    @Column(name = "RECEIPT_PACK_REMARK", length = 255)
    private String receiptPackRemark;

    @Column(name = "RECEIPT_TEST_QTY", precision = 15, scale = 4)
    private BigDecimal receiptTestQty;

    @Column(name = "RECEIPT_UNUSED_QTY", precision = 15, scale = 4)
    private BigDecimal receiptUnusedQty;

    @Column(name = "RETURN_CK", length = 1)
    private String returnCk;
}
