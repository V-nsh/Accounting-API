package com.accounting.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accounting.api.entity.SaleBill;

public interface SaleBillRepository extends JpaRepository<SaleBill, Long> {
    
}
