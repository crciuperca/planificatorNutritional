package services;

import dao.AllergyRepository;
import entities.Allergy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AllergyService {

    @Autowired
    AllergyRepository allergyRepository;

    public List<Allergy> getAllAlergies() {
        List allergyRepositoryList = new ArrayList<>();
        allergyRepository.findAll().forEach(allergyRepositoryList::add);
        return allergyRepositoryList;
    }
}
