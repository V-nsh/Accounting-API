package com.accounting.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accounting.api.entity.PurchaseCycle;

public interface PurchaseCycleRepository extends JpaRepository<PurchaseCycle, Long>{
    public PurchaseCycle findByPurchaseBillId(Long purchaseBillId);
}
