package entities;

import accounts.UserRoles;
import org.springframework.beans.factory.annotation.Autowired;
/*import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;*/
import org.springframework.data.annotation.CreatedDate;
import services.UserAccountService;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name="USER_ACCOUNT")
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_account_seq")
    @SequenceGenerator(sequenceName = "user_account_seq", allocationSize = 1, name = "user_account_seq")
    private long id;
    @Column
    private String username;
    @Column
    private String password;
    @Column(name="enabled", columnDefinition="char(1) default 'Y'")
    private char enabled;
    @CreatedDate
    @Column(name="CREATION_DATE")
    private Date creationDate;

    public UserAccount() {
        this.id = 0;
        this.username = "user";
        this.password = "user";
        this.enabled = 'Y';
    }

    public UserAccount(long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.enabled = 'Y';
    }

    public UserAccount(String username, String password) {
        this.username = username;
        this.password = password;
        this.enabled = 'Y';
        this.creationDate = new Date();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public char getEnabled() {
        return enabled;
    }

    public void setEnabled(char enabled) {
        this.enabled = enabled;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    /*
    @Override
    public boolean isEnabled(){
        return (this.enabled == 'Y' ? true : false);
    }

    @Override
    public boolean isCredentialsNonExpired(){
        return (this.enabled == 'Y' ? true : false);
    }

    @Override
    public boolean isAccountNonLocked(){
        return (this.enabled == 'Y' ? true : false);
    }

    @Override
    public boolean isAccountNonExpired(){
        return (this.enabled == 'Y' ? true : false);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(UserRoles.ADMIN));
        return authorities;
    }*/
}
