package entities;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="USER_WEIGHT_LOSS_PLAN")
public class UserPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_plan_seq")
    @SequenceGenerator(sequenceName = "user_plan_seq", allocationSize = 1, name = "user_plan_seq")
    private long id;
    @Column(name="USER_ID")
    private long userId;
    @Column(name="DIET_ID")
    private long dietId;
    @Column(name="TARGET_WEIGHT")
    private Double targetWeight;
    @Column(name="TARGET_DATE")
    private Date targetDate;
    @Column(name="MEALS_PER_DAY")
    private long mealsPerDay;
    @Column(name="ACTIVITY_LEVEL")
    private String activityLevel;

    public UserPlan() {

    }

    public UserPlan(Double targetWeight, Date targetDate, long mealsPerDay, String activityLevel) {
        this.targetWeight = targetWeight;
        this.targetDate = targetDate;
        this.mealsPerDay = mealsPerDay;
        this.activityLevel = activityLevel;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getDietId() {
        return dietId;
    }

    public void setDietId(long dietId) {
        this.dietId = dietId;
    }

    public Double getTargetWeight() {
        return targetWeight;
    }

    public void setTargetWeight(Double targetWeight) {
        this.targetWeight = targetWeight;
    }

    public Date getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(Date targetDate) {
        this.targetDate = targetDate;
    }

    public long getMealsPerDay() {
        return mealsPerDay;
    }

    public void setMealsPerDay(long mealsPerDay) {
        this.mealsPerDay = mealsPerDay;
    }

    public String getActivityLevel() {
        return activityLevel;
    }

    public void setActivityLevel(String activityLevel) {
        this.activityLevel = activityLevel;
    }
}
