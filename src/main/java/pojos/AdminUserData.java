package pojos;

public class AdminUserData {
    String username;
    String name;
    String email;
    String role;
    Boolean enabled;
    String creationDate;

    public AdminUserData() {
    }

    public AdminUserData(String username, String name, String email, String role, Boolean enabled, String creationDate) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.role = role;
        this.enabled = enabled;
        this.creationDate = creationDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }
}
