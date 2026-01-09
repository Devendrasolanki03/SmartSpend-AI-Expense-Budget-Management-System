package com.example.demo.entity;



import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ai_insights")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AiInsight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long insightId;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String insightText;


    @Enumerated(EnumType.STRING)
    private InsightType insightType;   // SAVINGS / BUDGET / WARNING / ANALYSIS

    private LocalDateTime createdAt;

    // ================= RELATIONSHIPS =================

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    // Optional category-specific insight
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @PrePersist
    void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
