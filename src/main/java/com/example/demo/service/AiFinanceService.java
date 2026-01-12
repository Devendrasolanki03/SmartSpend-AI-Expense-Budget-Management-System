package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Expense;
import com.example.demo.entity.InsightType;
import com.example.demo.entity.User;
import com.example.demo.exception.AiServiceException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ExpenseRepository;
import com.example.demo.repository.UserRepository;

@Service
public class AiFinanceService {

    private final ChatClient chatClient;
    private final UserRepository userRepo;
    private final ExpenseRepository expenseRepo;
    private final AiInsightService insightService;

    public AiFinanceService(ChatClient chatClient,
                            UserRepository userRepo,
                            ExpenseRepository expenseRepo,
                            AiInsightService insightService) {

        this.chatClient = chatClient;
        this.userRepo = userRepo;
        this.expenseRepo = expenseRepo;
        this.insightService = insightService;
    }

    // ================== GENERATE AI ADVICE ==================
    public String generateAdvice(String email) {

        User user = getUserByEmail(email);
        List<Expense> expenses = expenseRepo.findByUserWithCategory(user);

        String locationContext = buildLocationContext(user);
        String prompt = buildPrompt(expenses, locationContext);
        String advice = callAi(prompt);

        if (advice != null && !advice.isBlank()) {
            insightService.saveInsight(
                    email,                 // ✅ SECURE
                    advice,
                    InsightType.ANALYSIS
            );
        }

        return advice;
    }

    // ================== CHAT WITH AI ==================
    public String chat(String email, String userQuery) {

        if (userQuery == null || userQuery.trim().isEmpty()) {
            throw new IllegalArgumentException("Query cannot be empty");
        }

        User user = getUserByEmail(email);

        String locationContext = buildLocationContext(user);
        String securedPrompt = """
            %s

            User question (personal finance related only):
            %s
            """.formatted(locationContext, userQuery);

        String response = callAi(securedPrompt);

        if (response != null && !response.isBlank()) {
            insightService.saveInsight(
                    email,                // ✅ SECURE
                    response,
                    InsightType.SAVINGS
            );
        }

        return response;
    }

    // ================== COMMON AI CALL ==================
    private String callAi(String prompt) {

        try {
            String response = chatClient.prompt()
                    .system("""
                        You are 'Dhan-Guru', a professional Indian personal finance advisor.
                        Rules:
                        - Use ₹ currency
                        - Follow 50-30-20 budgeting rule
                        - Adjust advice based on city if provided
                        - Give only finance-related advice
                        - Keep responses short, practical, and realistic
                        """)
                    .user(prompt)
                    .call()
                    .content();

            if (response == null || response.isBlank()) {
                throw new AiServiceException("Empty response from ChatGPT");
            }

            return response.trim();

        } catch (Exception e) {
            throw new AiServiceException("ChatGPT processing failed", e);
        }
    }

    // ================== USER FETCH ==================
    private User getUserByEmail(String email) {

        return userRepo.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found with email: " + email));
    }

    // ================== LOCATION CONTEXT ==================
    private String buildLocationContext(User user) {

        if (user.getCity() != null && user.getCountry() != null) {
            return "User lives in " + user.getCity() + ", " + user.getCountry()
                    + ". Consider local cost of living.";
        }

        return "User location not specified. Give general Indian financial advice.";
    }

    // ================== PROMPT BUILDER ==================
    private String buildPrompt(List<Expense> expenses, String locationContext) {

        if (expenses.isEmpty()) {
            return """
                %s

                I have no expenses yet.
                Guide me on budgeting for beginners in India.
                """.formatted(locationContext);
        }

        String expenseData = expenses.stream()
                .map(e -> String.format(
                        "- %s: ₹%.2f (%s)",
                        e.getCategory().getName(),   // already correct
                        e.getAmount(),
                        e.getDescription() != null ? e.getDescription() : "No description"
                ))
                .collect(Collectors.joining("\n"));

        return """
            %s

            My Expense Report:
            %s

            Please provide:
            1. Overspending analysis
            2. 3 cost-cutting tips
            3. One monthly habit improvement
            4. One financial warning
            """.formatted(locationContext, expenseData);
    }
}
