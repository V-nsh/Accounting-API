package com.accounting.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accounting.api.entity.SaleCycle;

public interface SaleCycleRepository extends JpaRepository<SaleCycle, Long> {
    public SaleCycle findBySaleBillId(Long saleBillId);
}
