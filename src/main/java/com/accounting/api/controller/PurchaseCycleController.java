package com.accounting.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import com.accounting.api.service.PurchaseCycleService;

@RestController
@RequestMapping("/purchase-cycle")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PurchaseCycleController {

    private final PurchaseCycleService purchaseCycleService;

    /*
     * Get request: /purchase-cycle
     * get all purchase cycles.
     */
    @GetMapping()
    public ResponseEntity<?> getAllPurchaseCycle() {
        try {
            return new ResponseEntity<>(purchaseCycleService.getAllPurchaseCycles(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * Get request: /purchase-cycle/{purchaseBillId}
     * get one purchase cycle by purchase bill id.
     */
    @GetMapping("/{purchaseBillId}")
    public ResponseEntity<?> getOnePurchaseCycle(@PathVariable Long purchaseBillId) {
        try {
            return new ResponseEntity<>(purchaseCycleService.getPurchaseCycleByPurchaseBillId(purchaseBillId),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * Put request: /purchase-cycle/{purchaseBillId}
     * settle purchase bill by purchase bill id.
     */
    @PutMapping("/settlePurchaseBill/{purchaseBillId}")
    public ResponseEntity<?> settlePurchaseBill(@PathVariable Long purchaseBillId) {
        try {
            purchaseCycleService.settlePurchaseBill(purchaseBillId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            // return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
