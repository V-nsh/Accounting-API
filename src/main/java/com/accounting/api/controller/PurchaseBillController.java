package com.accounting.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import com.accounting.api.entity.PurchaseBill;
import com.accounting.api.service.PurchaseBillService;

@RestController
@RequestMapping("/purchase-bill")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PurchaseBillController {
    
    private final PurchaseBillService purchaseBillService;

    @GetMapping()
    public ResponseEntity<?> getAllPurchaseBill() {
        try {
            return new ResponseEntity<>(purchaseBillService.getAllPurchaseBills(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getOnePurchaseBill(@PathVariable Long purchaseBillId) {
        try {
            PurchaseBill purchaseBill = purchaseBillService.getPurchaseBillById(purchaseBillId);
            if (purchaseBill != null) {
                return new ResponseEntity<>(purchaseBill, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping()
    public ResponseEntity<?> createPurchaseBill(@RequestBody PurchaseBill purchaseBill) {
        try {
            PurchaseBill newPurchaseBill = purchaseBillService.createPurchaseBill(purchaseBill);
            if (newPurchaseBill != null) {
                return new ResponseEntity<>(newPurchaseBill, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("/{purchaseBillId}")
    public ResponseEntity<?> updatePurchaseBill(@RequestBody PurchaseBill purchaseBill, @PathVariable Long purchaseBillId) {
        try {
            PurchaseBill updatedPurchaseBill = purchaseBillService.updatePurchaseBill(purchaseBill, purchaseBillId);
            if (updatedPurchaseBill != null) {
                return new ResponseEntity<>(updatedPurchaseBill, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("/{purchaseBillId}")
    public ResponseEntity<?> destroyPurchaseBill(@PathVariable Long purchaseBillId) {
        try {
            purchaseBillService.deletePurchaseBill(purchaseBillId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
