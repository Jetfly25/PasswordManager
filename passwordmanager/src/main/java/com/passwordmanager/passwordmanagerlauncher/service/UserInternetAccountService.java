package com.passwordmanager.passwordmanagerlauncher.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.passwordmanager.passwordmanagerlauncher.database.UserInternetAccountDatabase;
import com.passwordmanager.passwordmanagerlauncher.user.UserInternetAccount;

@Service
public class UserInternetAccountService {
    
    @Autowired
    private UserInternetAccountDatabase accountDatabase;

    public void addPassword(UserInternetAccount account){
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

    public UserInternetAccount getPasswordEntryByURL(String URL) {
        return accountDatabase.findByURL(URL);
    }
}
