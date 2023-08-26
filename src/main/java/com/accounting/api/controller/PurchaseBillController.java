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
import com.accounting.api.entity.PurchaseCycle;
import com.accounting.api.service.PurchaseBillService;
import com.accounting.api.service.PurchaseCycleService;

@RestController
@RequestMapping("/purchase-bill")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PurchaseBillController {
    
    private final PurchaseBillService purchaseBillService;
    private final PurchaseCycleService purchaseCycleService;

    /*
     * Get request: /purchase-bill
     * get all purchase bills.
     */
    @GetMapping()
    public ResponseEntity<?> getAllPurchaseBill() {
        try {
            return new ResponseEntity<>(purchaseBillService.getAllPurchaseBills(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    

    /*
     * Get request: /purchase-bill/{purchaseBillId}
     * get one purchase bill by purchase bill id.
     */
    @GetMapping("/{purchaseBillId}")
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
    
    /*
     * Post request: /purchase-bill
     * create a purchase bill.
     */
    @PostMapping()
    public ResponseEntity<?> createPurchaseBill(@RequestBody PurchaseBill purchaseBill) {
        try {
            PurchaseBill newPurchaseBill = purchaseBillService.createPurchaseBill(purchaseBill);
            if (newPurchaseBill != null) {
                PurchaseCycle purchaseCycle = purchaseCycleService.createPurchaseCycle(newPurchaseBill);
                if(purchaseCycle == null){
                    // if creating purchase cycle fails, delete the purchase bill
                    purchaseBillService.deletePurchaseBill(newPurchaseBill.getPurchaseBillId());
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
                return new ResponseEntity<>(newPurchaseBill, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /*
     * Put request: /purchase-bill/{purchaseBillId}
     * update a purchase bill by purchase bill id.
     */
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
    
    /*
     * Delete request: /purchase-bill/{purchaseBillId}
     * delete a purchase bill by purchase bill id.
     */
    @DeleteMapping("/{purchaseBillId}")
    public ResponseEntity<?> destroyPurchaseBill(@PathVariable Long purchaseBillId) {
        try {
            if(purchaseBillService.deletePurchaseBill(purchaseBillId)){
                purchaseCycleService.deletePurchaseCycle(purchaseBillId);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
