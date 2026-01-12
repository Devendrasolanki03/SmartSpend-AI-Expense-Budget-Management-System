package com.example.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Expense;
import com.example.demo.entity.User;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    @Query("""
        SELECT e FROM Expense e
        JOIN FETCH e.category
        WHERE e.user = :user
    """)
    List<Expense> findByUserWithCategory(@Param("user") User user);
}
