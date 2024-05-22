package com.alquieventos.models;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    protected String identifiacion;
    protected String name;
    protected String phoneNumber;
    protected String email;

    public User() {

    }

    public String getIdentificacion() {
        return identifiacion;
    }

    public void setId(String identificacion) {
        this.identifiacion = identifiacion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
