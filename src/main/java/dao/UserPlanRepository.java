package dao;

import entities.UserPlan;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UserPlanRepository  extends CrudRepository<UserPlan, Long> {

    @Query("SELECT up from UserPlan up where up.userId = :userId and rownum = 1 order by up.id desc")
    public UserPlan findUserPlanByUserId(@Param("userId") long userId);

    @Query("SELECT up.id from UserPlan up where up.userId = :userId  and rownum = 1 order by up.id desc")
    public long findUserPlanIdByUserId(@Param("userId") long userId);

    /*TODO - Should update whole object*/
    @Modifying
    @Query("UPDATE UserPlan up SET up.targetWeight = :tWeight WHERE up.userId = :id")
    public void updateTWeight(@Param("id") long id, @Param("tWeight") double tWeight);

    @Modifying
    @Query("UPDATE UserPlan up SET up.targetDate = :tDate WHERE up.userId = :id")
    public void updateTDate(@Param("id") long id, @Param("tDate") Date tDate);

    @Modifying
    @Query("UPDATE UserPlan up SET up.mealsPerDay = :mealsPerDay WHERE up.userId = :id")
    public void updateMealsPerDay(@Param("id") long id, @Param("mealsPerDay") long mealsPerDay);

    @Modifying
    @Query("UPDATE UserPlan up SET up.activityLevel = :activityLevel WHERE up.userId = :id")
    public void updateActivityLevel(@Param("id") long id, @Param("activityLevel") String activityLevel);

    @Modifying
    @Query("UPDATE UserPlan up SET up.dietId = :dietId WHERE up.userId = :id")
    public void updateDietId(@Param("id") long id, @Param("dietId") long dietId);
}
