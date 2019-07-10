package services;

import dao.DietRepository;
import entities.Diet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DietService {

    @Autowired
    DietRepository dietRepository;

    public long getDietId(String dietName) {
        return dietRepository.getDietId(dietName);
    }

    public Diet getDietById(long id) {
        return dietRepository.getDietById(id);
    }

    public List<String> getDietList() {
        return dietRepository.getAllDiets();
    }
}
