package services;

import dao.UserPlanRepository;
import entities.UserPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserPlanService {

    @Autowired
    UserPlanRepository userPlanRepository;

    public void addPlan(UserPlan userPlan) {
        userPlanRepository.save(userPlan);
    }

    public long getPlanId(long userId) {
        return userPlanRepository.findUserPlanIdByUserId(userId);
    }

    public UserPlan getPlanByUserId(long userId) {
        return userPlanRepository.findUserPlanByUserId(userId);
    }

    @Transactional
    public void updateUserPlanInfo(long id, long dietId, double tWeight, Date tDate, long mealsPerDay, String activityLevel) {
        userPlanRepository.updateDietId(id, dietId);
        userPlanRepository.updateTWeight(id, tWeight);
        userPlanRepository.updateTDate(id, tDate);
        userPlanRepository.updateMealsPerDay(id, mealsPerDay);
        userPlanRepository.updateActivityLevel(id, activityLevel);
    }
}
