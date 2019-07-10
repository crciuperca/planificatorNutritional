package Utils;

import Constants.NutritionConstants;
import entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import pojos.FoodQtyPair;
import pojos.UserMenuData;
import services.FoodService;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/* Contains all logic for Caloric expenditure, macros and meal generation
* Uses Schofield Ecuations*/

public class NutritionLogicUtil {

    @Autowired
    private FoodService foodService;

    public static double getTotalEnergyExpenditure(double weight, char sex, String activityLevel, Date birthday) {
        int age = getYearsBetween(birthday, new Date());
        double TEEKiloJoules = 0;
        weight *= 1000;
        switch (sex) {
            case 'F':
                if (age < 30) {
                    TEEKiloJoules = NutritionConstants.ratioYoungFemale * weight + NutritionConstants.minimumExpenditureYoungFemale;
                } else {
                    TEEKiloJoules = NutritionConstants.ratioOldFemale * weight + NutritionConstants.minimumExpenditureOldFemale;
                }
                if (activityLevel.equals("Low")) {
                    TEEKiloJoules *= NutritionConstants.PALLightFemale;
                } else if (activityLevel.equals("Medium")) {
                    TEEKiloJoules *= NutritionConstants.PALModerateFemale;
                } else if (activityLevel.equals("High")) {
                    TEEKiloJoules *= NutritionConstants.PALHeavyFemale;
                }
                break;

            case 'M':
                if (age < 30) {
                    TEEKiloJoules = NutritionConstants.ratioYoungMale * weight + NutritionConstants.minimumExpenditureYoungMale;
                } else {
                    TEEKiloJoules = NutritionConstants.ratioOldMale * weight + NutritionConstants.minimumExpenditureOldMale;
                }
                if (activityLevel.equals("Low")) {
                    TEEKiloJoules *= NutritionConstants.PALLightMale;
                } else if (activityLevel.equals("Medium")) {
                    TEEKiloJoules *= NutritionConstants.PALModerateMale;
                } else if (activityLevel.equals("High")) {
                    TEEKiloJoules *= NutritionConstants.PALHeavyMale;
                }
                break;
        }

        return KJtoKCal(TEEKiloJoules);
    }

    public static int getYearsBetween(Date first, Date last) {
        Calendar a = getCalendar(first);
        Calendar b = getCalendar(last);
        int age = b.get(Calendar.YEAR) - a.get(Calendar.YEAR);
        if (a.get(Calendar.MONTH) > b.get(Calendar.MONTH) ||
                (a.get(Calendar.MONTH) == b.get(Calendar.MONTH) && a.get(Calendar.DATE) > b.get(Calendar.DATE))) {
            age--;
        }
        return age;
    }

    public static Calendar getCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    public static double KJtoKCal(double KJ) {
        return KJ / 4.184f;
    }

    public static long getTodayCalories(double weight, double targetWeight, Date startDate, Date targetDate, long consumedCalories, char sex, String activityLevel, Date birthday) {
        long TEE = Math.round(getTotalEnergyExpenditure(weight, sex, activityLevel, birthday));
        double weightLoss = weight - targetWeight;
        long totalCaloricDeficit = Math.round(weightLoss * NutritionConstants.KCalsPerKilogram);
        long actualCaloricDeficit = getDaysBetween(startDate, new Date()) * TEE - consumedCalories;

        return TEE - (totalCaloricDeficit - actualCaloricDeficit) / getDaysBetween(new Date(), targetDate);
    }

    public static long getDaysBetween(Date dateBefore, Date dateAfter) {
        long diff = dateAfter.getTime() - dateBefore.getTime();
        return diff / (1000 * 60 * 60 * 24);
    }

    public static List<UserMenu> createUserMenu(UserProgress todayUserProgress, Diet userDiet, UserPlan userPlan,
                                             List<Food> snackList, List<Food> meatList, List<Food> vegetableList,
                                             List<Food> fruitList, List<Food> sidedishList) {
        List<UserMenu> newUserMenu = new ArrayList<>();

        long mealCalories = todayUserProgress.getCalories() / userPlan.getMealsPerDay();
        long snackCalories = 0;
        if (userPlan.getMealsPerDay() > 3) {
            mealCalories = todayUserProgress.getCalories()  / 4;
            snackCalories = todayUserProgress.getCalories() / 4 / (userPlan.getMealsPerDay() - 3);
        }
        long mealProteinCalories = mealCalories * userDiet.getProteinRatio() / 100;
        long mealCarbCalories = mealCalories * userDiet.getCarbRatio() / 100;
        long mealFatCalories = mealCalories * userDiet.getLipidRatio() / 100;

        long snackProteinCalories = snackCalories * userDiet.getProteinRatio() / 100;
        long snackCarbCalories = snackCalories * userDiet.getCarbRatio() / 100;
        long snackFatCalories = snackCalories * userDiet.getLipidRatio() / 100;

        for(int i = 1; i <= userPlan.getMealsPerDay(); i++){
            if(i < 4) {
                newUserMenu.addAll(calculateMeal(i, mealProteinCalories, mealCarbCalories, mealFatCalories,
                        snackList, meatList, vegetableList, fruitList, sidedishList, todayUserProgress));
            } else {
                newUserMenu.addAll(calculateMeal(i, snackProteinCalories, snackCarbCalories, snackFatCalories,
                        snackList, meatList, vegetableList, fruitList, sidedishList, todayUserProgress));
            }
        }

        return newUserMenu;
    }

    public static List<UserMenu> calculateMeal(int mealNo, long proteinCalories, long carbCalories, long fatCalories,
                                               List<Food> snackList, List<Food> meatList, List<Food> vegetableList,
                                               List<Food> fruitList, List<Food> sidedishList, UserProgress todayUserProgress) {
        List<UserMenu> newMeal = new ArrayList<>();
        double meatQty = 0;
        double sidedishtQty = 0;
        double vegetableQty = 0;

        Collections.shuffle(meatList);
        Collections.shuffle(sidedishList);
        Collections.shuffle(vegetableList);
        Collections.shuffle(snackList);
        Collections.shuffle(fruitList);

        Food chosenMeat = meatList.get(0);
        Food chosenSidedish = sidedishList.get(0);
        Food chosenVegetable = vegetableList.get(0);
        if(mealNo > 3) {
            chosenMeat = snackList.get(0);
            chosenSidedish = vegetableList.get(0);
            chosenVegetable = fruitList.get(0);
        }
        UserMenu meat = new UserMenu();
        UserMenu sidedish = new UserMenu();
        UserMenu vegetables = new UserMenu();
        meat.setDay(todayUserProgress.getDay());
        meat.setUserId(todayUserProgress.getUserId());
        meat.setMealno(mealNo);
        sidedish.setDay(todayUserProgress.getDay());
        sidedish.setUserId(todayUserProgress.getUserId());
        sidedish.setMealno(mealNo);
        vegetables.setDay(todayUserProgress.getDay());
        vegetables.setUserId(todayUserProgress.getUserId());
        vegetables.setMealno(mealNo);

        meat.setFoodId(chosenMeat.getId());
        meatQty = getQuantity(chosenMeat, proteinCalories, carbCalories, fatCalories);
        meat.setQuantity((long)(meatQty*100));

        proteinCalories -= meatQty * chosenMeat.getProteins() * NutritionConstants.proteinCals;
        fatCalories -= meatQty * chosenMeat.getFats() * NutritionConstants.fatCals;
        carbCalories -= meatQty * chosenMeat.getCarbs() * NutritionConstants.carbCals;

        sidedish.setFoodId(chosenSidedish.getId());
        sidedishtQty = getQuantity(chosenSidedish, proteinCalories, carbCalories, fatCalories);
        sidedish.setQuantity((long)(sidedishtQty*100));

        proteinCalories -= sidedishtQty * chosenSidedish.getProteins() * NutritionConstants.proteinCals;
        fatCalories -= sidedishtQty * chosenSidedish.getFats() * NutritionConstants.fatCals;
        carbCalories -= sidedishtQty * chosenSidedish.getCarbs() * NutritionConstants.carbCals;

        vegetables.setFoodId(chosenVegetable.getId());
        vegetableQty = getQuantity(chosenVegetable, proteinCalories, carbCalories, fatCalories);
        vegetables.setQuantity((long)(vegetableQty*100));

        newMeal.add(meat);
        newMeal.add(sidedish);
        newMeal.add(vegetables);

        return newMeal;
    }

    public static double getQuantity(Food chosenFood, long proteinCalories, long carbCalories, long fatCalories) {
        double proteinGrams = Math.max(0f,(double)proteinCalories / NutritionConstants.proteinCals);
        double carbGrams = Math.max(0f,(double)carbCalories / NutritionConstants.carbCals);
        double fatGrams = Math.max(0f,(double)fatCalories / NutritionConstants.fatCals);
        double proteinRatio = (double)proteinGrams / chosenFood.getProteins();
        double carbRatio = (double)carbGrams / chosenFood.getCarbs();
        double fatRatio = (double)fatGrams / chosenFood.getFats();
        double minRatio = Math.min(Math.min((double)proteinRatio == 0 ? 10 : (double)proteinRatio,
                (double)carbRatio == 0 ? 10 : (double)carbRatio),
                (double)fatRatio == 0 ? 10 : (double)fatRatio);

        return minRatio > 9 ? minRatio/10 : minRatio;
    }

}
