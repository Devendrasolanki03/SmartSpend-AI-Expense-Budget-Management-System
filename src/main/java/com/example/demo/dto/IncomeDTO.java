package com.example.demo.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class IncomeDTO {

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be greater than zero")
    private Double amount;

    @NotBlank(message = "Income source is required")
    private String source;

    @NotNull(message = "Income date is required")
    private LocalDate incomeDate;

    public IncomeDTO() {
    }

    public IncomeDTO(Double amount, String source, LocalDate incomeDate) {
        this.amount = amount;
        this.source = source;
        this.incomeDate = incomeDate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public LocalDate getIncomeDate() {
        return incomeDate;
    }

    public void setIncomeDate(LocalDate incomeDate) {
        this.incomeDate = incomeDate;
    }
}
