package com.example.DB.Table;

import java.io.Serializable;

public class User implements Serializable {
    private String id;
    private String password;

    public User(){
    }

    public User(String id,String password){
        this.id=id;
        this.password=password;
    }

    public String getId(){
        return this.id;
    }
    public void setId(String id){
        this.id=id;
    }

    public String getPassword(){
        return this.password;
    }
    public void setPassword(String password){
        this.password=password;
    }
}
