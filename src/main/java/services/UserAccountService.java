package services;

import dao.RoleRepository;
import dao.UserAccountRepository;
import dao.UserRoleRepository;
import entities.UserAccount;
import entities.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserAccountService {


    @Autowired
    private UserAccountRepository userAccountRepository;

    @Transactional
    public void enableUserAccount(long id) {
        userAccountRepository.enableUserAccount(id);
    }

    @Transactional
    public void disableUserAccount(long id) {
        userAccountRepository.disableUserAccount(id);
    }

    public long getUserId(String username) {
        return userAccountRepository.findUserAccountByUsername(username).getId();
    }


    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private RoleRepository roleRepository;
    private List<UserAccount> userAccountList = new ArrayList<>(Arrays.asList(new UserAccount(), new UserAccount(1, "dude2", "pass2")));
    private long accountNo = 2;

    public List<UserAccount> getAllUserAccounts() {
        return userAccountRepository.getAllUserAccounts();
    }

    public UserAccount getUser(long id) {
        return userAccountRepository.findUserAccountById(id);
    }

    public void addUser(UserAccount userAccount) {
        userAccountRepository.save(userAccount);
    }


    public Long getUserId(String username, String password) {
        return userAccountRepository.findUserIdByCredentials(username, password);
    }

    @Transactional
    public void updateAccountPassword(long id, String password) {
        userAccountRepository.updatePassword(id, password);
    }

    public void addUser(UserAccount userAccount, String id) {
        if (Integer.parseInt(id) > userAccountList.size())
            return;

        for (int i = 0; i < userAccountList.size(); i++) {
            UserAccount auxUserAccount = userAccountList.get(i);
            if (auxUserAccount.getId() == Integer.parseInt(id)) {
                userAccount.setId(auxUserAccount.getId());
                userAccountList.set(Integer.parseInt(id), userAccount);
            }
        }
    }

    public void removeUser(String id) {
        userAccountList.removeIf(u -> u.getId() == Integer.parseInt(id));
    }

    public String createUser() {
        long size1 = userAccountRepository.count();
        userAccountRepository.save(new UserAccount("testuser", "testpass"));
        return ((userAccountRepository.count() > size1) ? "Done" : "Fail");
    }
    public long getNewId() {
        accountNo++;
        return accountNo - 1;
    }

    public boolean userExists(String username) {
        List<UserAccount> allUserAccountsList = new ArrayList<>();
        userAccountRepository.findAll().forEach(allUserAccountsList::add);

        for (UserAccount userAccount : allUserAccountsList) {
            if (username.toLowerCase().equals(userAccount.getUsername().toLowerCase())) {
                return true;
            }
        }
        return false;
    }

}
