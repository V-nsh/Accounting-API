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

import com.accounting.api.service.SaleCycleService;

@RestController
@RequestMapping("/sale-cycle")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SaleCycleController {

    private final SaleCycleService SaleCycleService;

    /*
     * Get request: /sale-cycle
     * get all sale cycles.
     */
    @GetMapping()
    public ResponseEntity<?> getAllSaleCycle() {
        try {
            return new ResponseEntity<>(SaleCycleService.getAllSaleCycles(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * Get request: /sale-cycle/{SaleBillId}
     * get one sale cycle by sale bill id.
     */
    @GetMapping("/{SaleBillId}")
    public ResponseEntity<?> getOneSaleCycle(@PathVariable Long SaleBillId) {
        try {
            return new ResponseEntity<>(SaleCycleService.getSaleCycleBySaleBillId(SaleBillId),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * Put request: /sale-cycle/{SaleBillId}
     * settle sale bill by sale bill id.
     */
    @PutMapping("/settleSaleBill/{SaleBillId}")
    public ResponseEntity<?> settleSaleBill(@PathVariable Long SaleBillId) {
        try {
            SaleCycleService.settleSaleBill(SaleBillId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            // return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
