package com.passwordmanager.passwordmanagerlauncher.database;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.passwordmanager.passwordmanagerlauncher.user.Users;

public interface UserDatabase extends JpaRepository<Users, Long>{
    Optional<Users> findByUsername(String username);
}
