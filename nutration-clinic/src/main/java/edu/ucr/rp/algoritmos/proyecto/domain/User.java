package edu.ucr.rp.algoritmos.proyecto.domain;

public class User {
    private String name, email, password;
    private Integer phoneNumber, iD, rol;

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public User setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public Integer getiD() {
        return iD;
    }

    public User setiD(Integer iD) {
        this.iD = iD;
        return this;
    }

    public Integer getRol() {
        return rol;
    }

    public User setRol(Integer rol) {
        this.rol = rol;
        return this;
    }
}
