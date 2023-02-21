package com.sparklab.car_rental.Login_Package;

public class Login_Model {
    private String fname;
    private  String contact;
    private String email;
    private String gender;
    private String username;
    private String password;

    public Login_Model() {
    }

    public Login_Model(String fname, String contact, String email, String gender, String username, String password) {
        this.fname = fname;
        this.contact = contact;
        this.email = email;
        this.gender = gender;
        this.username = username;
        this.password = password;
    }


    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
