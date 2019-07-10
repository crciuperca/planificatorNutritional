package dao;

import entities.UserProgress;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UserProgressRepository extends CrudRepository<UserProgress, Long> {

    @Query("SELECT up FROM UserProgress up where up.userId = :userId ORDER BY up.day ASC")
    public List<UserProgress> getUserProgressById(@Param("userId") long userId);

    @Query("SELECT SUM(up.calories) FROM UserProgress up where up.userId = :userId")
    public Long getTotalUserCalories(@Param("userId") long userId);

    @Query("SELECT up FROM UserProgress up where up.userId = :userId and up.day = (select MAX(upp.day) from UserProgress upp where upp.userId = :userId) ORDER BY up.day DESC")
    public UserProgress getLastProgressById(@Param("userId") long userId);

    @Modifying
    @Query("UPDATE UserProgress up SET up.calories = :calories WHERE up.day = :today AND up.userId = :userId")
    public void updateCalories(@Param("userId") long userId, @Param("calories") long calories, @Param("today") Date today);
}
