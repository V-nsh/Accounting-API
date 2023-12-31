package com.accounting.api.entity;

import java.time.LocalDate;

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
public class SaleCycle {
    @Id
    private long saleBillId;

    @OneToOne
    @PrimaryKeyJoinColumn
    private SaleBill saleBill;

    private LocalDate settledDate;
    private boolean isSettled;
}
