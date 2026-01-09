package com.example.demo.service;

import java.time.YearMonth;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class ReportService {

    // Example: Monthly expense summary
    public Map<String, Double> getMonthlyExpenseSummary(String email, YearMonth month) {
        // logic will be added later
        return Map.of();
    }

    // Example: Yearly income vs expense report
    public Map<String, Double> getYearlyReport(String email, int year) {
        // logic will be added later
        return Map.of();
    }
}
