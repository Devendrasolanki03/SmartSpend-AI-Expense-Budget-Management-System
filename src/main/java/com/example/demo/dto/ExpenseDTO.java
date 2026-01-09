package com.example.demo.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ExpenseDTO {

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be greater than zero")
    private Double amount;

    @NotNull(message = "Category ID is required")
    private Long categoryId;

    private String description;

    @NotNull(message = "Expense date is required")
    private LocalDate expenseDate;

    public ExpenseDTO() {
    }

    public ExpenseDTO(Double amount, Long categoryId, String description, LocalDate expenseDate) {
        this.amount = amount;
        this.categoryId = categoryId;
        this.description = description;
        this.expenseDate = expenseDate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(LocalDate expenseDate) {
        this.expenseDate = expenseDate;
    }
}
