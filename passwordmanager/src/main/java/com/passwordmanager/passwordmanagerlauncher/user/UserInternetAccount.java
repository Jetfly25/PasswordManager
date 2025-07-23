package com.passwordmanager.passwordmanagerlauncher.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class UserInternetAccount {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    private String URL;
    private String username;
    private String password;

    @ManyToOne
    private Users user;

    public UserInternetAccount(String URL, String username, String password){
        this.URL = URL;
        this.username = username;
        this.password = password;
    }

    public Long getID(){
        return ID;
    }

    public void setID(Long ID){
        this.ID = ID;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getURL(){
        return URL;
    }

    public void setURL(String URL){
        this.URL = URL;
    }

    public Users getUser(){
        return user;
    }

    public void setUser(Users user){
        this.user = user;
    }
}
