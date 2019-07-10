package dao;

import entities.UserData;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Repository
public interface UserDataRepository extends CrudRepository<UserData, Long> {

    @Query("SELECT ud FROM UserData ud WHERE ud.userId = :userId and rownum = 1 order by ud.id asc")
    public UserData getUserDataByUserId(@Param("userId") long userId);

    @Modifying
    @Query("UPDATE UserData ud SET ud.email = :email WHERE ud.userId = :id")
    public void updateEmail(@Param("id") long id, @Param("email") String email);

    @Query("SELECT ud.userId FROM UserData ud WHERE ud.email = :email and rownum = 1 order by ud.id asc")
    public Long findIdByEmail(@Param("email") String email);


    /*TODO - Should update whole object*/
    @Modifying
    @Query("UPDATE UserData ud SET ud.firstName = :firstName, ud.lastName = :lastName, ud.sex = :sex WHERE ud.userId = :id")
    public void updateName(@Param("id") long id, @Param("firstName") String firstName, @Param("lastName") String lastName, @Param("sex") char sex);

    @Modifying
    @Query("UPDATE UserData ud SET ud.weightMeasureUnit = :wUnit, ud.heightMeasureUnit = :hUnit WHERE ud.userId = :id")
    public void updateUnits(@Param("id") long id, @Param("hUnit") String hUnit, @Param("wUnit") String wUnit);

    @Modifying
    @Query("UPDATE UserData ud SET ud.birthdate = :birthday WHERE ud.userId = :id")
    public void updateBirthday(@Param("id") long id, @Param("birthday") Date birthday);

    @Modifying
    @Query("UPDATE UserData ud SET ud.height = :height WHERE ud.userId = :id")
    public void updateHeight(@Param("id") long id, @Param("height") double height);

    @Modifying
    @Query("UPDATE UserData ud SET ud.weight = :weight WHERE ud.userId = :id")
    public void updateWeight(@Param("id") long id, @Param("weight") double weight);
}
