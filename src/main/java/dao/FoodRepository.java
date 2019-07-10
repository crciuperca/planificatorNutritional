package dao;

import entities.Food;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepository extends CrudRepository<Food, Long> {

    @Query("SELECT f FROM Food f WHERE f.isMeat = 'Y' AND f.allergyId not in (:allergyIds) ORDER BY f.id")
    public List<Food> getMeatsWithoutAllergy(@Param("allergyIds") long[] allergyIds);

    @Query("SELECT f FROM Food f WHERE f.isSnack = 'Y' AND f.allergyId not in (:allergyIds) ORDER BY f.id")
    public List<Food> getSnacksWithoutAllergy(@Param("allergyIds") long[] allergyIds);

    @Query("SELECT f FROM Food f WHERE f.isVegetable = 'Y' AND f.allergyId not in (:allergyIds) ORDER BY f.id")
    public List<Food> getVegetablestWithoutAllergy(@Param("allergyIds") long[] allergyIds);

    @Query("SELECT f FROM Food f WHERE f.isFruit = 'Y' AND f.allergyId not in (:allergyIds) ORDER BY f.id")
    public List<Food> getFruitsWithoutAllergy(@Param("allergyIds") long[] allergyIds);

    @Query("SELECT f FROM Food f WHERE f.isSidedish = 'Y' AND f.allergyId not in (:allergyIds) ORDER BY f.id")
    public List<Food> getSidedishesWithoutAllergy(@Param("allergyIds") long[] allergyIds);

    @Query("SELECT f FROM Food f WHERE f.id = :id")
    public Food getFoodById(@Param("id") long id);
}
