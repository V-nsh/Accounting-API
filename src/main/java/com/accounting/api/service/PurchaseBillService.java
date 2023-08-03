package com.accounting.api.service;

import com.accounting.api.repository.PurchaseBillRepository;
import com.accounting.api.entity.PurchaseBill;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PurchaseBillService {
    private final PurchaseBillRepository purchaseBillRepository;

    public PurchaseBill createPurchaseBill(PurchaseBill purchaseBill) {
        return purchaseBillRepository.save(purchaseBill);
    }

    public PurchaseBill getPurchaseBillById(Long purchaseBillId) {
        return purchaseBillRepository.findById(purchaseBillId).orElse(null);
    }

    public List<PurchaseBill> getAllPurchaseBills() {
        return purchaseBillRepository.findAll();
    }

    public PurchaseBill updatePurchaseBill(PurchaseBill newPurchaseBill, Long purchaseBillId) {
        PurchaseBill existingPurchaseBill = getPurchaseBillById(purchaseBillId);
        if (existingPurchaseBill != null) {
            BeanUtils.copyProperties(newPurchaseBill, existingPurchaseBill, "purchaseBillId");
            return purchaseBillRepository.save(existingPurchaseBill);
        } else {
            return null;
        }
    }

    public Boolean deletePurchaseBill(Long purchaseBillId) {
        try {
            purchaseBillRepository.deleteById(purchaseBillId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
