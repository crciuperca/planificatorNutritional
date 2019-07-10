package services;

import dao.RoleRepository;
import dao.UserRoleRepository;
import entities.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleService {

    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    RoleRepository roleRepository;

    public void addNewUser(long userId) {
        userRoleRepository.save(new UserRole(userId, 11L, 'Y'));
    }

    public Long getUserRoleId(long userId) {
        return userRoleRepository.findUserRoleByUserId(userId);
    }

    public String getUserRoleName(long userId) {
        return (getUserRoleId(userId) == null || roleRepository.getRoleName(getUserRoleId(userId)) == null) ? "" : roleRepository.getRoleName(getUserRoleId(userId));
    }
}
