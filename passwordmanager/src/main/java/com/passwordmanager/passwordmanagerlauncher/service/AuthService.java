package com.passwordmanager.passwordmanagerlauncher.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.passwordmanager.passwordmanagerlauncher.database.UserDatabase;
import com.passwordmanager.passwordmanagerlauncher.user.Users;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private UserDatabase userDatabase;

    // This method simply uses spring security's built-in login function to authenticate a user, instead of a custom made one
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userDatabase.findByUsername(username);

        // Return a nonexistant or "fake" user if the user does not exist
        if (user == null){
            return User.builder()
                .username("guest")
                .password("")
                .roles("GUEST")
                .build();
        }

        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles("USER")
                .build();
    }
}
