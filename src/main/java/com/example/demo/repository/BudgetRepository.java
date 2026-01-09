package com.example.demo.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Budget;
import com.example.demo.entity.User;

public interface BudgetRepository extends JpaRepository<Budget, Long> {
public 	List<Budget> findByUserUserId(Long userId);
List<Budget> findByUser(User user);

}