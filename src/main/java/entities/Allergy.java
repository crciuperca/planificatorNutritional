package entities;

import javax.persistence.*;

@Entity
@Table(name="ALLERGIES")
public class Allergy {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "allergy_seq")
    @SequenceGenerator(sequenceName = "allergy_seq", allocationSize = 1, name = "allergy_seq")
    private long id;
    @Column(name = "TYPE")
    private String allergyType;
    @Column(name = "PICTURE_NAME")
    private String pictureName;

    public Allergy() {}

    public Allergy(String allergyType, String pictureName) {
        this.allergyType = allergyType;
        this.pictureName = pictureName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAllergyType() {
        return allergyType;
    }

    public void setAllergyType(String allergyType) {
        this.allergyType = allergyType;
    }

    public String getPictureName() {
        return pictureName;
    }

    public void setPictureName(String pictureName) {
        this.pictureName = pictureName;
    }
}
