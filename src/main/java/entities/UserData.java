package entities;

import org.apache.catalina.User;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="USER_DATA")
public class UserData {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_data_seq")
    @SequenceGenerator(sequenceName = "user_data_seq", allocationSize = 1, name = "user_data_seq")
    private Long id;
    @Column(name="USER_ID")
    private Long userId;
    @Column(name="EMAIL")
    private String email;
    @Column(name="FIRST_NAME")
    private String firstName;
    @Column(name="LAST_NAME")
    private String lastName;
    @Column(name="HEIGHT")
    private Double height;
    @Column(name="HEIGHT_MEASURE_UNIT", columnDefinition="varchar2(2) default 'cm'")
    private String heightMeasureUnit;
    @Column(name="WEIGHT")
    private Double weight;
    @Column(name="WEIGHT_MEASURE_UNIT", columnDefinition="varchar2(2) default 'kg'")
    private String weightMeasureUnit;
    @Column(name="BIRTHDATE")
    private Date birthdate;
    @Column(name="PHOTO_ID")
    private Long photoID;
    @Column(name="WEIGHT_LOSS_PLAN_ID")
    private Long weightLossPlanId;
    @Column(name="SEX")
    private char sex;


    public UserData(){

    }

    public UserData(Long userId, String email, String firstName, String lastName, Double height, String heightMeasureUnit, Double weight, String weightMeasureUnit, Date birthdate, Long photoID, Long weightLossPlanId, char sex) {
        this.userId = userId;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.height = height;
        this.heightMeasureUnit = heightMeasureUnit;
        this.weight = weight;
        this.weightMeasureUnit = weightMeasureUnit;
        this.birthdate = birthdate;
        this.photoID = photoID;
        this.weightLossPlanId = weightLossPlanId;
        this.sex = sex;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public String getHeightMeasureUnit() {
        return heightMeasureUnit;
    }

    public void setHeightMeasureUnit(String heightMeasureUnit) {
        this.heightMeasureUnit = heightMeasureUnit;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getWeightMeasureUnit() {
        return weightMeasureUnit;
    }

    public void setWeightMeasureUnit(String weightMeasureUnit) {
        this.weightMeasureUnit = weightMeasureUnit;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Long getPhotoID() {
        return photoID;
    }

    public void setPhotoID(Long photoID) {
        this.photoID = photoID;
    }

    public Long getWeightLossPlanId() {
        return weightLossPlanId;
    }

    public void setWeightLossPlanId(Long weightLossPlanId) {
        this.weightLossPlanId = weightLossPlanId;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }
}
