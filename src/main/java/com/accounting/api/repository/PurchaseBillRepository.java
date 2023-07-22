package com.accounting.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accounting.api.entity.PurchaseBill;

public interface PurchaseBillRepository extends JpaRepository<PurchaseBill, Long> {
    
}
