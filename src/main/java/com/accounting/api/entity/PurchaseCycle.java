package com.accounting.api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
public class PurchaseCycle {

    @Id
    private Long purchaseBillId;

    @OneToOne
    @PrimaryKeyJoinColumn
    private PurchaseBill purchaseBill;

    private String settledDate;
}
