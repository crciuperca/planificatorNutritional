package dao;

import entities.UserAccount;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

import static javafx.scene.input.KeyCode.T;

@Repository
public class UserAccountRepositoryImpl{// implements UserAccountRepository {

    /*@PersistenceContext(unitName = "Schema")
    protected EntityManager em;  //<<-----------------


    @Override
    public Optional<UserAccount> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<UserAccount> findAll() {
        return null;
    }

    @Override
    public Iterable<UserAccount> findAllById(Iterable<Long> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(UserAccount userAccount) {

    }

    @Override
    public void deleteAll(Iterable<? extends UserAccount> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public UserAccount findUserAccountByUsername(String username) {
        return null;
    }

    @Override
    public UserAccount findUserAccountById(Long id) {
        return null;
    }*/
}
