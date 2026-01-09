package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.ExpenseDTO;
import com.example.demo.dto.IncomeDTO;
import com.example.demo.entity.Category;
import com.example.demo.entity.Expense;
import com.example.demo.entity.Income;
import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ExpenseRepository;
import com.example.demo.repository.IncomeRepository;
import com.example.demo.repository.UserRepository;

@Service
public class FinanceService {

    private final UserRepository userRepo;
    private final IncomeRepository incomeRepo;
    private final ExpenseRepository expenseRepo;
    private final CategoryRepository categoryRepo;

    public FinanceService(UserRepository userRepo,
                          IncomeRepository incomeRepo,
                          ExpenseRepository expenseRepo,
                          CategoryRepository categoryRepo) {
        this.userRepo = userRepo;
        this.incomeRepo = incomeRepo;
        this.expenseRepo = expenseRepo;
        this.categoryRepo = categoryRepo;
    }

    // ---------------- ADD INCOME ----------------
    public Income addIncome(String email, IncomeDTO dto) {

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Income income = new Income();
        income.setAmount(dto.getAmount());
        income.setSource(dto.getSource());
        income.setIncomeDate(dto.getIncomeDate());
        income.setUser(user);

        return incomeRepo.save(income);
    }

    // ---------------- ADD EXPENSE ----------------
    public Expense addExpense(String email, ExpenseDTO dto) {

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Category category = categoryRepo.findById(dto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        Expense expense = new Expense();
        expense.setAmount(dto.getAmount());
        expense.setCategory(category); // ✅ FIXED
        expense.setDescription(dto.getDescription());
        expense.setExpenseDate(dto.getExpenseDate());
        expense.setUser(user);

        return expenseRepo.save(expense);
    }

    // ---------------- GET EXPENSES ----------------
    public List<Expense> getExpenses(String email) {

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return expenseRepo.findByUser(user);
    }
}
