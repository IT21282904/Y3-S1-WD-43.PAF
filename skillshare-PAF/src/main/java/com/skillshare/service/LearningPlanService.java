package com.skillshare.service;

import com.skillshare.model.LearningPlan;
import com.skillshare.repository.LearningPlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class LearningPlanService {

    private final LearningPlanRepository learningPlanRepo;

    public LearningPlan createPlan(LearningPlan plan) {
        return learningPlanRepo.save(plan);
    }

    public List<LearningPlan> getAllPlans() {
        return learningPlanRepo.findAll();
    }

    public List<LearningPlan> getPlansByUserId(String userId) {
        return learningPlanRepo.findByUserId(userId);
    }

    public Optional<LearningPlan> getPlanById(String id) {
        return learningPlanRepo.findById(id);
    }

    public Optional<LearningPlan> updatePlan(String id, LearningPlan updated) {
        return learningPlanRepo.findById(id).map(plan -> {
            plan.setTitle(updated.getTitle());
            plan.setDescription(updated.getDescription());
            plan.setTopics(updated.getTopics());
            plan.setDeadline(updated.getDeadline());
            return learningPlanRepo.save(plan);
        });
    }

    public void deletePlan(String id) {
        learningPlanRepo.deleteById(id);
    }
}