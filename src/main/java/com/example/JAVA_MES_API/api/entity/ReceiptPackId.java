package com.example.JAVA_MES_API.api.entity;


import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
public class ReceiptPackId implements Serializable {

    private String receiptNo;
    private Integer receiptSeq;
    private Integer receiptPackSeq;
}
