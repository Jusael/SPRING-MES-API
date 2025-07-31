
package com.example.JAVA_MES_API.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.example.JAVA_MES_API.api.entity.Cell;

@Repository
public interface CellRepository extends JpaRepository<Cell, String> {


}