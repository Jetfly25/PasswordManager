package com.passwordmanager.passwordmanagerlauncher.database;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.passwordmanager.passwordmanagerlauncher.user.User;

public interface UserDatabase extends JpaRepository<User, Long>{
    Optional<User> findByUsername(String username);
}
