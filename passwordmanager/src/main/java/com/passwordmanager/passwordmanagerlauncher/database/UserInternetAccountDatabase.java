package com.passwordmanager.passwordmanagerlauncher.database;

import org.springframework.data.jpa.repository.JpaRepository;

import com.passwordmanager.passwordmanagerlauncher.user.UserInternetAccount;

public interface UserInternetAccountDatabase extends JpaRepository<UserInternetAccount, Long>{
    UserInternetAccount findByURL(String URL);
}
