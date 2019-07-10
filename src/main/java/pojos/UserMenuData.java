package pojos;

import entities.UserMenu;

import java.util.List;

public class UserMenuData {
    long mealsPerDay;
    List<FoodQtyPair> meal1;
    List<FoodQtyPair> snack1;
    List<FoodQtyPair> meal2;
    List<FoodQtyPair> snack2;
    List<FoodQtyPair> meal3;

    public UserMenuData() {

    }

    public UserMenuData(long mealsPerDay, List<FoodQtyPair> meal1, List<FoodQtyPair> snack1, List<FoodQtyPair> meal2, List<FoodQtyPair> snack2, List<FoodQtyPair> meal3) {
        this.mealsPerDay = mealsPerDay;
        this.meal1 = meal1;
        this.snack1 = snack1;
        this.meal2 = meal2;
        this.snack2 = snack2;
        this.meal3 = meal3;
    }

    public long getMealsPerDay() {
        return mealsPerDay;
    }

    public void setMealsPerDay(long mealsPerDay) {
        this.mealsPerDay = mealsPerDay;
    }

    public List<FoodQtyPair> getMeal1() {
        return meal1;
    }

    public void setMeal1(List<FoodQtyPair> meal1) {
        this.meal1 = meal1;
    }

    public List<FoodQtyPair> getSnack1() {
        return snack1;
    }

    public void setSnack1(List<FoodQtyPair> snack1) {
        this.snack1 = snack1;
    }

    public List<FoodQtyPair> getMeal2() {
        return meal2;
    }

    public void setMeal2(List<FoodQtyPair> meal2) {
        this.meal2 = meal2;
    }

    public List<FoodQtyPair> getSnack2() {
        return snack2;
    }

    public void setSnack2(List<FoodQtyPair> snack2) {
        this.snack2 = snack2;
    }

    public List<FoodQtyPair> getMeal3() {
        return meal3;
    }

    public void setMeal3(List<FoodQtyPair> meal3) {
        this.meal3 = meal3;
    }
}
