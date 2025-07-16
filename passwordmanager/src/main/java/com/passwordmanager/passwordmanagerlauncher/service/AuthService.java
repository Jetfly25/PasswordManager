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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userDatabase.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User " + username + "not found!"));

        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles("USER")
                .build();
    }
}
