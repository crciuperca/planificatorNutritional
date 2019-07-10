package services;

import dao.UserAllergyRepository;
import entities.UserAllergy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAllergyService {

    @Autowired
    private UserAllergyRepository userAllergyRepository;

    public void addAllergy(long userId, long allergyId) {
        userAllergyRepository.save(new UserAllergy(userId, allergyId));
    }

    public List<Long> getUserAllergies(long userId) {
        return userAllergyRepository.getUserAllergies(userId);
    }
}
