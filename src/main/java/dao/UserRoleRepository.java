package dao;

import entities.UserRole;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository  extends CrudRepository<UserRole, Long> {

    @Query("SELECT ur.roleId from UserRole ur WHERE ur.userId = :userId AND rownum = 1 ORDER BY ur.id")
    public Long findUserRoleByUserId(@Param("userId") long userId);


}
