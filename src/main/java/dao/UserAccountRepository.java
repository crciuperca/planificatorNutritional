package dao;

import entities.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserAccountRepository extends CrudRepository<UserAccount, Long> {

    @Modifying
    @Query("UPDATE UserAccount ua SET ua.password = :password WHERE ua.id = :id")
    public void updatePassword(@Param("id") long id, @Param("password") String password);

    @Query("SELECT ua FROM UserAccount ua order by ua.id asc")
    public List<UserAccount> getAllUserAccounts();

    @Modifying
    @Query("UPDATE UserAccount ua SET ua.enabled = 'Y' WHERE ua.id = :id")
    public void enableUserAccount(@Param("id") long id);

    @Modifying
    @Query("UPDATE UserAccount ua SET ua.enabled = 'N' WHERE ua.id = :id")
    public void disableUserAccount(@Param("id") long id);

    @Query("SELECT ua FROM UserAccount ua WHERE ua.username = :username and rownum = 1 order by ua.id asc")
    public UserAccount findUserAccountByUsername(@Param("username") String username);

    @Query("SELECT ua FROM UserAccount ua WHERE ua.id = :id and rownum = 1 order by ua.id asc")
    public UserAccount findUserAccountById(@Param("id") long id);

    @Query("SELECT ua.id FROM UserAccount ua WHERE ua.username = :username and ua.password = :password and rownum = 1 order by ua.id asc")
    public Long findUserIdByCredentials(@Param("username") String username, @Param("password") String password);


}
