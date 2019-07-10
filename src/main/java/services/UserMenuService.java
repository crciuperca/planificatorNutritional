package services;

import dao.UserMenuRepository;
import entities.UserMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserMenuService {

    @Autowired
    UserMenuRepository userMenuRepository;

    public List<UserMenu> getLatestUserMenu(long userId) {
        return userMenuRepository.getLatestUserMenu(userId);
    }

    public void addUserMenu(UserMenu userMenu) {
        userMenuRepository.save(userMenu);
    }
}
