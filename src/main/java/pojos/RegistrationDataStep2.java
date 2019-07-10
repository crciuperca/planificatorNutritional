package pojos;

/* Information needed for the 2nd Step of Registration */

public class RegistrationDataStep2 {
    String firstName;
    String lastName;
    String sex;
    String height;
    String hUnit;
    String weight;
    String wUnit;
    String birthday;
    String tWeight;
    String tDate;
    String diet;
    String activityLevel;
    String mealsPerDay;
    String id;

    public RegistrationDataStep2() {

    }

    public RegistrationDataStep2(String firstName, String lastName, String sex, String height, String hUnit, String weight, String wUnit, String birthday, String tWeight, String tDate, String diet, String activityLevel, String mealsPerDay, String id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.sex = sex;
        this.height = height;
        this.hUnit = hUnit;
        this.weight = weight;
        this.wUnit = wUnit;
        this.birthday = birthday;
        this.tWeight = tWeight;
        this.tDate = tDate;
        this.diet = diet;
        this.activityLevel = activityLevel;
        this.mealsPerDay = mealsPerDay;
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String gethUnit() {
        return hUnit;
    }

    public void sethUnit(String hUnit) {
        this.hUnit = hUnit;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getwUnit() {
        return wUnit;
    }

    public void setwUnit(String wUnit) {
        this.wUnit = wUnit;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String gettWeight() {
        return tWeight;
    }

    public void settWeight(String tWeight) {
        this.tWeight = tWeight;
    }

    public String gettDate() {
        return tDate;
    }

    public void settDate(String tDate) {
        this.tDate = tDate;
    }

    public String getDiet() {
        return diet;
    }

    public void setDiet(String diet) {
        this.diet = diet;
    }

    public String getActivityLevel() {
        return activityLevel;
    }

    public void setActivityLevel(String activityLevel) {
        this.activityLevel = activityLevel;
    }

    public String getMealsPerDay() {
        return mealsPerDay;
    }

    public void setMealsPerDay(String mealsPerDay) {
        this.mealsPerDay = mealsPerDay;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
