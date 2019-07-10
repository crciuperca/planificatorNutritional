package entities;

import javax.persistence.*;

@Entity
@Table(name="USER_ALLERGY")
public class UserAllergy {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_allergy_seq")
    @SequenceGenerator(sequenceName = "user_allergy_seq", allocationSize = 1, name = "user_allergy_seq")
    private long id;
    @Column(name="USER_ID")
    private long userId;
    @Column(name="ALLERGY_ID")
    private long allergyID;

    public UserAllergy(long userId, long allergyID) {
        this.userId = userId;
        this.allergyID = allergyID;
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

    public long getAllergyID() {
        return allergyID;
    }

    public void setAllergyID(long allergyID) {
        this.allergyID = allergyID;
    }
}
