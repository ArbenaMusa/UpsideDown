package com.am.upsidedown.models;

public class User {

    public String name;
    public String surname;
    public String email;
    public String role;
    public String occupation;

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

}
