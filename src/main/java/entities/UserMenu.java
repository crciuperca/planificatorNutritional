package entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="USER_MENU")
public class UserMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_menu_seq")
    @SequenceGenerator(sequenceName = "user_menu_seq", allocationSize = 1, name = "user_menu_seq")
    private long id;
    @Column(name="USER_ID")
    private long userId;
    @Column(name="FOOD_ID")
    private long foodId;
    @Column(name="QUANTITY")
    private long quantity;
    @Column(name="DAY")
    private Date day;
    @Column(name="MEALNO")
    private long mealno;

    public UserMenu() {
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

    public long getFoodId() {
        return foodId;
    }

    public void setFoodId(long foodId) {
        this.foodId = foodId;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public long getMealno() {
        return mealno;
    }

    public void setMealno(long mealno) {
        this.mealno = mealno;
    }
}
