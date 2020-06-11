package edu.ucr.rp.algoritmos.proyecto.domain;

import edu.ucr.rp.algoritmos.proyecto.domain.util.Date;
import edu.ucr.rp.algoritmos.proyecto.logic.lists.LinkedList;

public class User {
    private String name, email, password;
    private Integer phoneNumber, iD, rol;
    private Date date;
    private LinkedList datesHistory; //TODO cambiar por LinkedList

    public User(String name, String email, String password, Integer phoneNumber, Integer iD, Integer rol, Date date, LinkedList datesHistory) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.iD = iD;
        this.rol = rol;
        this.date = date;
        this.datesHistory = datesHistory;
    }

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

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public User setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public int getiD() {
        return iD;
    }

    public User setiD(Integer iD) {
        this.iD = iD;
        return this;
    }

    public int getRol() {
        return rol;
    }

    public User setRol(Integer rol) {
        this.rol = rol;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public User setDate(Date date) {
        this.date = date;
        return this;
    }

    public LinkedList getDatesHistory() {
        return datesHistory;
    }

    public User setDatesHistory(LinkedList datesHistory) {
        this.datesHistory = datesHistory;
        return this;
    }
}
