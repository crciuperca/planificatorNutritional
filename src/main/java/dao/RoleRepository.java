package dao;

import entities.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

    @Query("SELECT r.roleName from Role r WHERE r.id = :id AND rownum = 1 ORDER BY r.id")
    public String getRoleName(@Param("id") long id);
}
