package entities;

import javax.persistence.*;

@Entity
@Table(name="FOOD")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "food_seq")
    @SequenceGenerator(sequenceName = "food_seq", allocationSize = 1, name = "food_seq")
    private long id;
    @Column(name="NAME")
    private String name;
    @Column(name="CALORIES")
    private long calories;
    @Column(name="PROTEINS")
    private long proteins;
    @Column(name="CARBS")
    private long carbs;
    @Column(name="FATS")
    private long fats;
    @Column(name="ALLERGY_ID")
    private long allergyId;
    @Column(name="MEASURE_UNIT")
    private String measureUnit;
    @Column(name="SNACK")
    private char isSnack;
    @Column(name="MEAT")
    private char isMeat;
    @Column(name="VEGETABLE")
    private char isVegetable;
    @Column(name="FRUIT")
    private char isFruit;
    @Column(name="SIDEDISH")
    private char isSidedish;

    public Food() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCalories() {
        return calories;
    }

    public void setCalories(long calories) {
        this.calories = calories;
    }

    public long getProteins() {
        return proteins;
    }

    public void setProteins(long proteins) {
        this.proteins = proteins;
    }

    public long getCarbs() {
        return carbs;
    }

    public void setCarbs(long carbs) {
        this.carbs = carbs;
    }

    public long getFats() {
        return fats;
    }

    public void setFats(long fats) {
        this.fats = fats;
    }

    public long getAllergyId() {
        return allergyId;
    }

    public void setAllergyId(long allergyId) {
        this.allergyId = allergyId;
    }

    public String getMeasureUnit() {
        return measureUnit;
    }

    public void setMeasureUnit(String measureUnit) {
        this.measureUnit = measureUnit;
    }

    public char getIsSnack() {
        return isSnack;
    }

    public void setIsSnack(char isSnack) {
        this.isSnack = isSnack;
    }

    public char getIsMeat() {
        return isMeat;
    }

    public void setIsMeat(char isMeat) {
        this.isMeat = isMeat;
    }

    public char getIsVegetable() {
        return isVegetable;
    }

    public void setIsVegetable(char isVegetable) {
        this.isVegetable = isVegetable;
    }

    public char getIsFruit() {
        return isFruit;
    }

    public void setIsFruit(char isFruit) {
        this.isFruit = isFruit;
    }

    public char getIsSidedish() {
        return isSidedish;
    }

    public void setIsSidedish(char isSidedish) {
        this.isSidedish = isSidedish;
    }
}
