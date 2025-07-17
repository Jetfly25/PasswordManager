package com.passwordmanager.passwordmanagerlauncher.service;

import java.util.List;

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
}
