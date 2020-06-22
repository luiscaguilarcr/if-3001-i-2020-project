package edu.ucr.rp.algoritmos.proyecto.logic.domain;

public class User {
    private String name, email, password, address;
    private int phoneNumber, ID, rol;

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

    public String getAddress() {
        return address;
    }

    public User setAddress(String address) {
        this.address = address;
        return this;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public User setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public int getID() {
        return ID;
    }

    public User setID(int ID) {
        this.ID = ID;
        return this;
    }

    public int getRol() {
        return rol;
    }

    public User setRol(int rol) {
        this.rol = rol;
        return this;
    }
}
