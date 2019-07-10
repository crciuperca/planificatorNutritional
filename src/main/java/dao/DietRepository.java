package dao;

import entities.Diet;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DietRepository extends CrudRepository<Diet, Long> {

    @Query("SELECT d.id FROM Diet d WHERE d.dietName = :dietName")
    public long getDietId(@Param("dietName") String dietName);

    @Query("SELECT d FROM Diet d WHERE d.id = :id")
    public Diet getDietById(@Param("id") long id);

    @Query("SELECT d.dietName from Diet d")
    public List<String> getAllDiets();
}
