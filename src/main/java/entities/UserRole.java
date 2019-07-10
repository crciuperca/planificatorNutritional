package entities;

import org.apache.catalina.User;

import javax.persistence.*;

@Entity
@Table(name="USER_ROLES")
public class UserRole{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_role_seq")
    @SequenceGenerator(sequenceName = "user_role_seq", allocationSize = 1, name = "user_role_seq")
    private Long id;
    @Column
    private Long userId;
    @Column
    private Long roleId;
    @Column(name="active", columnDefinition="char(1) default 'Y'")
    private char active;

    public UserRole() {}

    public UserRole(Long userId, Long roleId, char active) {
        this.userId = userId;
        this.roleId = roleId;
        this.active = active;
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

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public char getActive() {
        return active;
    }

    public void setActive(char active) {
        this.active = active;
    }
}
