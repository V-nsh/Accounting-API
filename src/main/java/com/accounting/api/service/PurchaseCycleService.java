package com.accounting.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.accounting.api.entity.PurchaseBill;
import com.accounting.api.entity.PurchaseCycle;
import com.accounting.api.repository.PurchaseCycleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PurchaseCycleService {
    private final PurchaseBillService purchaseBillService;
    private final PurchaseCycleRepository purchaseCycleRepository;

    // we can have a view where we show the bill with due date, each bill will have a button to settle the bill
    // when the button is clicked, we will call this method
    public void settlePurchaseBill(Long purchaseBillId, String settledDate) {
        // get the purchase bill by id
        // get the purchase cycle by purchase bill
        // set the settled date of purchase cycle to current date
        // save the purchase cycle
        PurchaseBill theBill = purchaseBillService.getPurchaseBillById(purchaseBillId);
        PurchaseCycle theCycle = purchaseCycleRepository.findByPurchaseBillId(purchaseBillId);
        if (theBill != null) {
            theCycle.setSettledDate(settledDate);
            purchaseCycleRepository.save(theCycle);
        }
    }

    // a method to create the purchase cycle, this cycle will be created WITH the bill behind the scenes.
    public PurchaseCycle createPurchaseCycle(PurchaseBill purchaseBill) {
        PurchaseCycle purchaseCycle = new PurchaseCycle();
        purchaseCycle.setPurchaseBill(purchaseBill);
        return purchaseCycleRepository.save(purchaseCycle);
    }

    public PurchaseCycle getPurchaseCycleByPurchaseBillId(Long purchaseBillId) {
        return purchaseCycleRepository.findByPurchaseBillId(purchaseBillId);
    }

    public List<PurchaseCycle> getAllPurchaseCycles() {
        return purchaseCycleRepository.findAll();
    }
}
