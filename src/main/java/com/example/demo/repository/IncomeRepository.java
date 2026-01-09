package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Income;
import com.example.demo.entity.User;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {

    List<Income> findByUser(User user);
}
