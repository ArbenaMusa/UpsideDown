package com.am.upsidedown.auth;

public class User {

    private String name;
    private String surname;
    private String workmanOrUser;
    private String occupation;
    private String email;

    public User(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getWorkmanOrUser() {
        return workmanOrUser;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setWorkmanOrUser(String workmanOrUser) {
        this.workmanOrUser = workmanOrUser;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
