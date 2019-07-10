package services;

import dao.UserProgressRepository;
import entities.UserProgress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class UserProgressService {

    @Autowired
    UserProgressRepository userProgressRepository;

    public List<UserProgress> getUserProgress(long userId) {
        return userProgressRepository.getUserProgressById(userId);
    }

    public UserProgress getLastUserProgress(long userId) {
        return userProgressRepository.getLastProgressById(userId);
    }

    @Transactional
    public void addUserProgress(long userId, Date day, long calories) {
        userProgressRepository.save(new UserProgress(userId, calories, day));
    }

    @Transactional
    public void updateUserCalories(long userId, long calories, Date day) {
        userProgressRepository.updateCalories(userId, calories, day);
    }

    public Long getTotalUserCalories(long userId) {
        Long total = userProgressRepository.getTotalUserCalories(userId);
        return total == null ? 0 : total;
    }
}
