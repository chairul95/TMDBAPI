package com.example.pacinterviewtesting.sql;

public class UserModel {
    int id;
    public String userName;
    public String password;
    public UserModel(int id, String userName, String password) {
        this.id = id;
        this.userName = userName;
        this.password = password;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }




}
