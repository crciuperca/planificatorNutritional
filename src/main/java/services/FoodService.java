package services;

import dao.FoodRepository;
import entities.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodService {

    @Autowired
    FoodRepository foodRepository;

    public List<Food> getMeatsWithoutAllergy(long[] allergyIds) {
        return foodRepository.getMeatsWithoutAllergy(allergyIds);
    }

    public List<Food> getSnacksWithoutAllergy(long[] allergyIds) {
        return foodRepository.getSnacksWithoutAllergy(allergyIds);
    }

    public List<Food> getVegetablestWithoutAllergy(long[] allergyIds) {
        return foodRepository.getVegetablestWithoutAllergy(allergyIds);
    }

    public List<Food> getFruitsWithoutAllergy(long[] allergyIds) {
        return foodRepository.getFruitsWithoutAllergy(allergyIds);
    }

    public List<Food> getSidedishesWithoutAllergy(long[] allergyIds) {
        return foodRepository.getSidedishesWithoutAllergy(allergyIds);
    }

    public Food getFoodById(long id) {
        return foodRepository.getFoodById(id);
    }
}
