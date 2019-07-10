package entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="USER_PROGRESS")
public class UserProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_progress_seq")
    @SequenceGenerator(sequenceName = "user_progress_seq", allocationSize = 1, name = "user_progress_seq")
    private long id;
    @Column(name = "USER_ID")
    private long userId;
    @Column(name = "CALORIES")
    private long calories;
    @Column(name = "DAY")
    private Date day;

    public UserProgress() {
    }

    public UserProgress(long userId, long calories, Date day) {
        this.userId = userId;
        this.calories = calories;
        this.day = day;
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

    public long getCalories() {
        return calories;
    }

    public void setCalories(long calories) {
        this.calories = calories;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }
}
