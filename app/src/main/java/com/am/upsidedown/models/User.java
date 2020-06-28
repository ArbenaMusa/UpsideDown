package com.am.upsidedown.models;

public class User {

    public String name;
    public String surname;
    public String email;
    public String role;
    public String occupation;
    public String phone;

    public User(String name, String surname, String email, String role, String phone) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.role = role;
        this.phone = phone;
    }

    public User(String name, String surname, String email, String role, String occupation, String phone) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.role = role;
        this.occupation = occupation;
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public String getSurname() {
        return surname;
    }

    public String getOccupation() {
        return occupation;
    }

    public String getPhone() {
        return phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
