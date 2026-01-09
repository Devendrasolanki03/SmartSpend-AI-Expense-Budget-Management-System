package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Expense;
import com.example.demo.entity.User;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    // Fetch expenses of a specific user
    List<Expense> findByUser(User user);
}
