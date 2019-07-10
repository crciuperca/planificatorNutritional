package services;

import dao.UserDataRepository;
import entities.UserData;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserDataService {

    @Autowired
    UserDataRepository userDataRepository;

    public List<UserData> getAllUserData(){
        List userDataRepositoryList = new ArrayList<>();
        userDataRepository.findAll().forEach(userDataRepositoryList::add);
        return userDataRepositoryList;
    }

    public void addData(UserData userData) {
        userDataRepository.save(userData);
    }

    public UserData getUserDataByUserId(long userId) {
        return userDataRepository.getUserDataByUserId(userId);
    }

    @Transactional
    public void updateDataEmail(long id, String email) {
        userDataRepository.updateEmail(id, email);
    }

    public Long getIdByEmail(String email) {
        return userDataRepository.findIdByEmail(email);
    }

    @Transactional
    public void updateUserDataInfo(long id, String firstName, String lastName, char sex, String hUnit,
                                   String wUnit, Date birthday, double height, double weight) {
        userDataRepository.updateName(id, firstName, lastName, sex);;
        userDataRepository.updateUnits(id, hUnit, wUnit);
        userDataRepository.updateBirthday(id, birthday);
        userDataRepository.updateHeight(id, height);
        userDataRepository.updateWeight(id, weight);
    }

}
