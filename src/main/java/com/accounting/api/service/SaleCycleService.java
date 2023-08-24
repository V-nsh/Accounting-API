package com.accounting.api.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.accounting.api.repository.SaleCycleRepository;
import com.accounting.api.entity.SaleBill;
import com.accounting.api.entity.SaleCycle;
@Service
@RequiredArgsConstructor
public class SaleCycleService {
    private final SaleBillService SaleBillService;
    private final SaleCycleRepository SaleCycleRepository;

    // we can have a view where we show the bill with due date, each bill will have a button to settle the bill
    // when the button is clicked, we will call this method
    public void settleSaleBill(Long SaleBillId) {
        SaleBill theBill = SaleBillService.getSaleBillById(SaleBillId);
        SaleCycle theCycle = SaleCycleRepository.findBySaleBillId(SaleBillId);
        if (theBill != null) {
            theCycle.setSettledDate(LocalDate.now());
            SaleCycleRepository.save(theCycle);
        }
    }

    // a method to create the Sale cycle, this cycle will be created WITH the bill behind the scenes.
    public SaleCycle createSaleCycle(SaleBill SaleBill) {
        SaleCycle SaleCycle = new SaleCycle();
        SaleCycle.setSaleBillId(SaleBill.getSaleBillId());
        SaleCycle.setSettledDate(null);
        return SaleCycleRepository.save(SaleCycle);
    }

    public SaleCycle getSaleCycleBySaleBillId(Long SaleBillId) {
        return SaleCycleRepository.findBySaleBillId(SaleBillId);
    }

    public List<SaleCycle> getAllSaleCycles() {
        return SaleCycleRepository.findAll();
    }

    public void deleteSaleCycle(Long SaleBillId) {
        SaleCycleRepository.deleteById(SaleBillId);
    }
}
