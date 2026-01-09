package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.AiInsight;
import com.example.demo.entity.InsightType;


@Repository
public interface AiInsightRepository extends JpaRepository<AiInsight, Long> {

	 // All insights of a user (latest first)
    List<AiInsight> findByUser_UserIdOrderByCreatedAtDesc(Long userId);

    // Secure delete (ownership check)
    Optional<AiInsight> findByInsightIdAndUser_UserId(Long insightId, Long userId);

    // Filter by insight type (SAVINGS / BUDGET / ANALYSIS)
    List<AiInsight> findByUser_UserIdAndInsightTypeOrderByCreatedAtDesc(
            Long userId, InsightType insightType
    );
}
