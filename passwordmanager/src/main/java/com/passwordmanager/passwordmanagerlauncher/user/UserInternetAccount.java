package com.passwordmanager.passwordmanagerlauncher.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class UserInternetAccount {
    
    @Id
    private Long ID;

    private String URL;
    private String username;
    private String password;

    public UserInternetAccount() {}

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
}
