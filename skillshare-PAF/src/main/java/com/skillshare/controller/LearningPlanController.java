package com.skillshare.controller;

import com.skillshare.model.LearningPlan;
import com.skillshare.service.LearningPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/learningplans")
@RequiredArgsConstructor
public class LearningPlanController {

    private final LearningPlanService learningPlanService;

    @PostMapping
    public ResponseEntity<?> createPlan(@RequestBody LearningPlan plan) {
        LearningPlan created = learningPlanService.createPlan(plan);
        return ResponseEntity.ok(Map.of("message", "Learning Plan created ✅", "plan", created));
    }

    @GetMapping
    public ResponseEntity<?> getAllPlans() {
        List<LearningPlan> plans = learningPlanService.getAllPlans();
        return ResponseEntity.ok(Map.of("count", plans.size(), "plans", plans));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getPlansByUser(@PathVariable String userId) {
        List<LearningPlan> plans = learningPlanService.getPlansByUserId(userId);
        return ResponseEntity.ok(Map.of("count", plans.size(), "plans", plans));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, LearningPlan>> getPlanById(@PathVariable String id) {
        return learningPlanService.getPlanById(id)
                .map(plan -> ResponseEntity.ok(Map.of("plan", plan)))
                .orElse(ResponseEntity.status(404).body(null));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePlan(@PathVariable String id, @RequestBody LearningPlan plan) {
        return learningPlanService.updatePlan(id, plan)
                .map(updated -> ResponseEntity.ok(Map.of("message", "Learning Plan updated ✅", "plan", updated)))
                .orElse(ResponseEntity.status(404).body(Map.of("error", "Learning Plan not found")));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePlan(@PathVariable String id) {
        learningPlanService.deletePlan(id);
        return ResponseEntity.ok(Map.of("message", "Learning Plan deleted ✅"));
    }
}