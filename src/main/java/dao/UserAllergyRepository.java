package dao;

import entities.UserAllergy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAllergyRepository extends CrudRepository<UserAllergy, Long> {

    @Query("SELECT ua.allergyID FROM UserAllergy ua WHERE ua.userId = :userId ORDER BY ua.id")
    public List<Long> getUserAllergies(@Param("userId") long userId);
}
