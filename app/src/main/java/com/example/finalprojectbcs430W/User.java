package com.example.finalprojectbcs430W;

public class User {
    public String email, name;

    public User() {

    }

    public User(String name, String email){
        this.name = name;
        this.email = email;
    }

    public String getName(){return name;}
    public void  setName(String n){this.name=n;}

    public String getEmail(){return email;}
    public void  setEmail(String e){this.email=e;}
}
