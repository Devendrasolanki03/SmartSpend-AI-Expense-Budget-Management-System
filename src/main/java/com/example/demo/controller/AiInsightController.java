package com.example.demo.controller;


import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.AiInsightResponseDTO;
import com.example.demo.entity.InsightType;
import com.example.demo.service.AiInsightService;

@RestController
@RequestMapping("/api/ai-insights")
public class AiInsightController {

    private final AiInsightService aiInsightService;

    public AiInsightController(AiInsightService aiInsightService) {
        this.aiInsightService = aiInsightService;
    }

    // ================= CREATE =================
    @PostMapping("/{userId}")
    public AiInsightResponseDTO createInsight(
            @PathVariable Long userId,
            @RequestParam InsightType insightType,
            @RequestBody String prompt) {

        return aiInsightService.saveInsight(
                userId,
                prompt,
                insightType
        );
    }

    // ================= GET ALL =================
    @GetMapping("/user/{userId}")
    public List<AiInsightResponseDTO> getInsights(@PathVariable Long userId) {
        return aiInsightService.getInsightsByUser(userId);
    }

    // ================= GET BY TYPE =================
    @GetMapping("/user/{userId}/type/{type}")
    public List<AiInsightResponseDTO> getByType(
            @PathVariable Long userId,
            @PathVariable InsightType type) {

        return aiInsightService.getInsightsByType(userId, type);
    }

    // ================= DELETE =================
    @DeleteMapping("/{insightId}/user/{userId}")
    public String deleteInsight(
            @PathVariable Long insightId,
            @PathVariable Long userId) {

        aiInsightService.deleteInsight(insightId, userId);
        return "AI insight deleted successfully";
    }
}
