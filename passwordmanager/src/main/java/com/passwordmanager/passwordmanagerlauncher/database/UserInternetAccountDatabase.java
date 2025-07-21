package com.passwordmanager.passwordmanagerlauncher.database;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.passwordmanager.passwordmanagerlauncher.user.UserInternetAccount;

public interface UserInternetAccountDatabase extends JpaRepository<UserInternetAccount, Long>{
    UserInternetAccount findByURL(String URL);
    List<UserInternetAccount> findByUser_Username(String username);
}
