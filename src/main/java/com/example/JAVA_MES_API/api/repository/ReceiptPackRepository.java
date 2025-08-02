package com.example.JAVA_MES_API.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.example.JAVA_MES_API.api.entity.ReceiptPack;
import com.example.JAVA_MES_API.api.entity.ReceiptPackId;

@Repository
public interface ReceiptPackRepository extends JpaRepository<ReceiptPack, ReceiptPackId> {

    ReceiptPack findByReceiptPackBarcodeNo(String receiptPackBarcodeNo);
    
}