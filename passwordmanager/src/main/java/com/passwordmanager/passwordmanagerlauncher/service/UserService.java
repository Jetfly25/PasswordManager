package com.passwordmanager.passwordmanagerlauncher.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.passwordmanager.passwordmanagerlauncher.database.UserDatabase;
import com.passwordmanager.passwordmanagerlauncher.user.Users;

@Service
public class UserService {
    
    @Autowired
    private UserDatabase userDatabase;
    
    @Autowired
    private PasswordEncoder encoder;

    public void registerUser(Users user){
        user.setPassword(encoder.encode(user.getPassword()));
        userDatabase.save(user);
    }
    public boolean deleteUser(String username) {
        Users user = userDatabase.findByUsername(username);
        if (user != null) {
            userDatabase.delete(user);
            return true;
        }
        return false;
    }

    public boolean changePassword(String username, String oldPassword, String newPassword, String confirmPassword) {
        Users user = userDatabase.findByUsername(username);
        if (user != null) {
            if (encoder.matches(oldPassword, user.getPassword())) {
                if (newPassword.equals(confirmPassword)) {
                    String encodedPassword = encoder.encode(newPassword);
                    user.setPassword(encodedPassword);
                    userDatabase.save(user);
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
        return false;
    }
}
