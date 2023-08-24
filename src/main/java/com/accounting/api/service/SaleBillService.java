package com.accounting.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.util.List;

import com.accounting.api.repository.SaleBillRepository;
import com.accounting.api.entity.SaleBill;

@Service
@RequiredArgsConstructor
public class SaleBillService {
    private final SaleBillRepository saleBillRepository;

    public SaleBill createSaleBill(SaleBill saleBill) {
        return saleBillRepository.save(saleBill);
    }

    public SaleBill getSaleBillById(Long saleBillId) {
        return saleBillRepository.findById(saleBillId).orElse(null);
    }

    public List<SaleBill> getAllSaleBills() {
        return saleBillRepository.findAll();
    }

    public SaleBill updateSaleBill(SaleBill newSaleBill, Long saleBillId) {
        SaleBill existingSaleBill = getSaleBillById(saleBillId);
        if (existingSaleBill != null) {
            BeanUtils.copyProperties(newSaleBill, existingSaleBill, "saleBillId");
            return saleBillRepository.save(existingSaleBill);
        } else {
            return null;
        }
    }

    public Boolean deleteSaleBill(Long saleBillId) {
        try {
            saleBillRepository.deleteById(saleBillId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
