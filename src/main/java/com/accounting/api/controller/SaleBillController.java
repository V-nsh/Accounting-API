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

import com.accounting.api.entity.SaleBill;
import com.accounting.api.entity.SaleCycle;
import com.accounting.api.service.SaleBillService;
import com.accounting.api.service.SaleCycleService;

@RestController
@RequestMapping("/sale-bill")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SaleBillController {
    
    private final SaleBillService SaleBillService;
    private final SaleCycleService SaleCycleService;

    /*
     * Get request: /sale-bill
     * get all sale bills.
     */
    @GetMapping()
    public ResponseEntity<?> getAllSaleBill() {
        try {
            return new ResponseEntity<>(SaleBillService.getAllSaleBills(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /*
     * Get request: /sale-bill/{SaleBillId}
     * get one sale bill by sale bill id.
     */
    @GetMapping("/{SaleBillId}")
    public ResponseEntity<?> getOneSaleBill(@PathVariable Long SaleBillId) {
        try {
            SaleBill SaleBill = SaleBillService.getSaleBillById(SaleBillId);
            if (SaleBill != null) {
                return new ResponseEntity<>(SaleBill, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /*
     * Post request: /sale-bill
     * create a new sale bill.
     */
    @PostMapping()
    public ResponseEntity<?> createSaleBill(@RequestBody SaleBill SaleBill) {
        try {
            SaleBill newSaleBill = SaleBillService.createSaleBill(SaleBill);
            if (newSaleBill != null) {
                SaleCycle SaleCycle = SaleCycleService.createSaleCycle(newSaleBill);
                if(SaleCycle == null){
                    // if we fail to create the cycle also delete the bill and return an error.
                    SaleBillService.deleteSaleBill(newSaleBill.getSaleBillId());
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
                return new ResponseEntity<>(newSaleBill, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /*
     * Put request: /sale-bill/{SaleBillId}
     * update a sale bill by sale bill id.
     */
    @PutMapping("/{SaleBillId}")
    public ResponseEntity<?> updateSaleBill(@RequestBody SaleBill SaleBill, @PathVariable Long SaleBillId) {
        try {
            SaleBill updatedSaleBill = SaleBillService.updateSaleBill(SaleBill, SaleBillId);
            if (updatedSaleBill != null) {
                return new ResponseEntity<>(updatedSaleBill, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    /*
     * Delete request: /sale-bill/{SaleBillId}
     * delete a sale bill by sale bill id.
     */
    @DeleteMapping("/{SaleBillId}")
    public ResponseEntity<?> destroySaleBill(@PathVariable Long SaleBillId) {
        try {
            if(SaleBillService.deleteSaleBill(SaleBillId)){
                SaleCycleService.deleteSaleCycle(SaleBillId);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
