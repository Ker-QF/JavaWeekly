package com.it.test;

public class User {
    private String userName;
    private String password;
    private String personId;
    private String phoneNumber;

    //alt+insert / alt + Fn + insert


    public User() {
    }

    public User(String userName, String password, String personId,String phoneNumber) {
        this.userName = userName;
        this.password = password;
        this.personId = personId;
        this.phoneNumber = phoneNumber;
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

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
