package com.example.demo.controller;
import java.security.Principal;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.AiFinanceService;
@RestController
@RequestMapping("/api/ai")
public class AiController {

    private final AiFinanceService aiService;

    public AiController(AiFinanceService aiService) {
        this.aiService = aiService;
    }

    @GetMapping("/advice")
    public ResponseEntity<String> advice(Principal principal) {
        return ResponseEntity.ok(
                aiService.generateAdvice(principal.getName())
        );
    }

    @PostMapping("/chat")
    public ResponseEntity<String> chat(
            @RequestBody String query,
            Principal principal) {

        return ResponseEntity.ok(
                aiService.chat(principal.getName(), query)
        );
    }
}
