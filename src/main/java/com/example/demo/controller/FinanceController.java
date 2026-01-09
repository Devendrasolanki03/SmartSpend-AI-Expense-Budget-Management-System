package com.example.demo.controller;

import jakarta.validation.Valid;

import java.security.Principal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.ExpenseDTO;
import com.example.demo.dto.IncomeDTO;
import com.example.demo.entity.Expense;
import com.example.demo.entity.Income;
import com.example.demo.service.FinanceService;

@RestController
@RequestMapping("/api/finance")
public class FinanceController {

    private final FinanceService financeService;

    public FinanceController(FinanceService financeService) {
        this.financeService = financeService;
    }

    // ---------------- ADD INCOME ----------------
    @PostMapping("/income")
    public ResponseEntity<Income> addIncome(
            Principal principal,
            @Valid @RequestBody IncomeDTO dto) {

        Income income = financeService.addIncome(principal.getName(), dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(income);
    }

    // ---------------- ADD EXPENSE ----------------
    @PostMapping("/expense")
    public ResponseEntity<Expense> addExpense(
            Principal principal,
            @Valid @RequestBody ExpenseDTO dto) {

        Expense expense = financeService.addExpense(principal.getName(), dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(expense);
    }

    // ---------------- GET EXPENSES ----------------
    @GetMapping("/expenses")
    public ResponseEntity<List<Expense>> getExpenses(Principal principal) {

        List<Expense> expenses =
                financeService.getExpenses(principal.getName());

        return ResponseEntity.ok(expenses);
    }
}
