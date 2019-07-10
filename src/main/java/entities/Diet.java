package entities;

import javax.persistence.*;

@Entity
@Table(name="DIETS")
public class Diet {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "diet_seq")
    @SequenceGenerator(sequenceName = "diet_seq", allocationSize = 1, name = "diet_seq")
    private long id;
    @Column(name="DIET_NAME")
    private String dietName;
    @Column(name="MEALS_PER_DAY")
    private Long mealsPerDay;
    @Column(name="PROTEIN_RATIO")
    private Long proteinRatio;
    @Column(name="CARB_RATIO")
    private Long carbRatio;
    @Column(name="LIPID_RATIO")
    private Long lipidRatio;
    @Column(name="CUSTOM", columnDefinition="char(1) default 'Y'")
    private char custom;

    public Diet() {

    }

    public Diet(Long mealsPerDay, Long proteinRatio, Long carbRatio, Long lipidRatio) {
        this.mealsPerDay = mealsPerDay;
        this.proteinRatio = proteinRatio;
        this.carbRatio = carbRatio;
        this.lipidRatio = lipidRatio;
        this.custom = 'Y';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDietName() {
        return dietName;
    }

    public void setDietName(String dietName) {
        this.dietName = dietName;
    }

    public Long getMealsPerDay() {
        return mealsPerDay;
    }

    public void setMealsPerDay(Long mealsPerDay) {
        this.mealsPerDay = mealsPerDay;
    }

    public Long getProteinRatio() {
        return proteinRatio;
    }

    public void setProteinRatio(Long proteinRatio) {
        this.proteinRatio = proteinRatio;
    }

    public Long getCarbRatio() {
        return carbRatio;
    }

    public void setCarbRatio(Long carbRatio) {
        this.carbRatio = carbRatio;
    }

    public Long getLipidRatio() {
        return lipidRatio;
    }

    public void setLipidRatio(Long lipidRatio) {
        this.lipidRatio = lipidRatio;
    }

    public char getCustom() {
        return custom;
    }

    public void setCustom(char custom) {
        this.custom = custom;
    }
}
