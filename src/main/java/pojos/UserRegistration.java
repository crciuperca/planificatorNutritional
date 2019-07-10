package pojos;

public class UserRegistration {

    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private Long height;
    private String heightUnit;
    private Long weight;
    private String weightUnit;
    private String birthday;
    private String diet;
    private Long targetWeight;
    private Long activityLevel;
    private String photoId;
    private Boolean allergyList[];

    public UserRegistration(){

    }

    public UserRegistration(String username, String password, String email, String firstName, String lastName, Long height, String heightUnit, Long weight, String weightUnit, String birthday, String diet, Long targetWeight, Long activityLevel, String photoId, Boolean[] allergyList) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.height = height;
        this.heightUnit = heightUnit;
        this.weight = weight;
        this.weightUnit = weightUnit;
        this.birthday = birthday;
        this.diet = diet;
        this.targetWeight = targetWeight;
        this.activityLevel = activityLevel;
        this.photoId = photoId;
        this.allergyList = allergyList;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Long getHeight() {
        return height;
    }

    public void setHeight(Long height) {
        this.height = height;
    }

    public String getHeightUnit() {
        return heightUnit;
    }

    public void setHeightUnit(String heightUnit) {
        this.heightUnit = heightUnit;
    }

    public Long getWeight() {
        return weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

    public String getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(String weightUnit) {
        this.weightUnit = weightUnit;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getDiet() {
        return diet;
    }

    public void setDiet(String diet) {
        this.diet = diet;
    }

    public Long getTargetWeight() {
        return targetWeight;
    }

    public void setTargetWeight(Long targetWeight) {
        this.targetWeight = targetWeight;
    }

    public Long getActivityLevel() {
        return activityLevel;
    }

    public void setActivityLevel(Long activityLevel) {
        this.activityLevel = activityLevel;
    }

    public String getPhotoId() {
        return photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    public Boolean[] getAllergyList() {
        return allergyList;
    }

    public void setAllergyList(Boolean[] allergyList) {
        this.allergyList = allergyList;
    }
}
