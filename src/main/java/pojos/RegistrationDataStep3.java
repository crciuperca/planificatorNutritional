package pojos;

/* Information needed for the 3rd Step of Registration */

public class RegistrationDataStep3 {
    boolean[] allergies;

    public RegistrationDataStep3() {

    }

    public RegistrationDataStep3(boolean[] allergies) {
        this.allergies = allergies;
    }

    public boolean[] getAllergies() {
        return allergies;
    }

    public void setAllergies(boolean[] allergies) {
        this.allergies = allergies;
    }
}
