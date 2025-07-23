package com.passwordmanager.passwordmanagerlauncher.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    private String username;
    private String password;

    public Users(String username, String password){
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
}
