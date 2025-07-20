package com.passwordmanager.passwordmanagerlauncher.database;

import org.springframework.data.jpa.repository.JpaRepository;

import com.passwordmanager.passwordmanagerlauncher.user.Users;

public interface UserDatabase extends JpaRepository<Users, Long>{
    Users findByUsername(String username);
    boolean existsByUsername(String username);
}
