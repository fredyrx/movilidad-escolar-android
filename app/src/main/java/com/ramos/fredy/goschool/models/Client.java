package com.ramos.fredy.goschool.models;

import java.util.List;

/**
 * Created by fredy on 30/04/17.
 */

public class Client extends User {
    private int id;
    private String name;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;
    private List<AddDependent> dependant;

    public List<AddDependent> getDependant() {
        return dependant;
    }

    public void setDependant(List<AddDependent> dependant) {
        this.dependant = dependant;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
