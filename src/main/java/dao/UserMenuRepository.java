package dao;

import entities.UserMenu;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMenuRepository extends CrudRepository<UserMenu, Long> {

    @Query("SELECT um FROM UserMenu um WHERE um.userId = :userId and um.day = (SELECT MAX(u.day) from UserMenu u where u.userId = :userId)")
    public List<UserMenu> getLatestUserMenu(@Param("userId") long userId);

    @Query("SELECT um FROM UserMenu um WHERE um.userId = :userId and um.day = (SELECT MAX(u.day) from UserMenu u where u.userId = :userId) and um.mealno = 1")
    public List<UserMenu> getLatestUserMenu1(@Param("userId") long userId);

    @Query("SELECT um FROM UserMenu um WHERE um.userId = :userId and um.day = (SELECT MAX(u.day) from UserMenu u where u.userId = :userId) and um.mealno = 2")
    public List<UserMenu> getLatestUserMenu2(@Param("userId") long userId);

    @Query("SELECT um FROM UserMenu um WHERE um.userId = :userId and um.day = (SELECT MAX(u.day) from UserMenu u where u.userId = :userId) and um.mealno = 3")
    public List<UserMenu> getLatestUserMenu3(@Param("userId") long userId);

    @Query("SELECT um FROM UserMenu um WHERE um.userId = :userId and um.day = (SELECT MAX(u.day) from UserMenu u where u.userId = :userId) and um.mealno = 4")
    public List<UserMenu> getLatestUserMenu4(@Param("userId") long userId);

    @Query("SELECT um FROM UserMenu um WHERE um.userId = :userId and um.day = (SELECT MAX(u.day) from UserMenu u where u.userId = :userId) and um.mealno = 5")
    public List<UserMenu> getLatestUserMenu5(@Param("userId") long userId);
}
