package com.accounting.api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
public class PurchaseBill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long purchaseBillId;
    private String purchaseDate;
    private String seller;
    private long totalAmount;
    private long totalQuantity;
    private String dueDate;
}
