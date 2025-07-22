package com.passwordmanager.passwordmanagerlauncher.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.passwordmanager.passwordmanagerlauncher.database.UserDatabase;
import com.passwordmanager.passwordmanagerlauncher.database.UserInternetAccountDatabase;
import com.passwordmanager.passwordmanagerlauncher.user.UserInternetAccount;
import com.passwordmanager.passwordmanagerlauncher.user.Users;
import com.passwordmanager.passwordmanagerlauncher.util.Encryption;

@Service
public class UserInternetAccountService {
    
    @Autowired
    private UserInternetAccountDatabase accountDatabase;

    @Autowired
    private UserDatabase userDatabase;

    public void addPassword(UserInternetAccount account, String username) throws Exception {
        Users user = userDatabase.findByUsername(username);
        String encryptedPassword = Encryption.encryptPassword(account.getPassword());
        account.setUser(user);
        account.setPassword(encryptedPassword);
        accountDatabase.save(account);
    }

    public List<UserInternetAccount> getAllEntries() {
        return accountDatabase.findAll();
    }

    public void deletePassword(Long ID){
        Optional<UserInternetAccount> passwordEntry = accountDatabase.findById(ID);
        if (passwordEntry.isPresent()){
            accountDatabase.delete(passwordEntry.get());
        }
    }
    public UserInternetAccount getPasswordByID(Long ID){
        return accountDatabase.findById(ID).orElse(null);
    }

    public UserInternetAccount getPasswordEntryByURL(String URL, String username) {
        return accountDatabase.findByURLAndUser_Username(URL, username);
    }

    public void removeAllPasswords(){
        List<UserInternetAccount> accounts = getAllEntries();
        for (UserInternetAccount entry : accounts){
            accountDatabase.delete(entry);
        }
    }
    public List<UserInternetAccount> getEntriesForUser(String username) {
        return accountDatabase.findByUser_Username(username);
    }
}
