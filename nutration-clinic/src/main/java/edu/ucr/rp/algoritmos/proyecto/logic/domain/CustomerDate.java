package edu.ucr.rp.algoritmos.proyecto.logic.domain;

public class CustomerDate {
    private String date;
    private String hour;
    private int customerID, adminID, targetID;

    public String getDate() {
        return date;
    }

    public CustomerDate setDate(String date) {
        this.date = date;
        return this;
    }

    public String getHour() {
        return hour;
    }

    public CustomerDate setHour(String hour) {
        this.hour = hour;
        return this;
    }

    public int getCustomerID() {
        return customerID;
    }

    public CustomerDate setCustomerID(int customerID) {
        this.customerID = customerID;
        return this;
    }

    public int getAdminID() {
        return adminID;
    }

    public CustomerDate setAdminID(int adminID) {
        this.adminID = adminID;
        return this;
    }

    public int getTargetID() {
        return targetID;
    }

    public CustomerDate setTargetID(int targetID) {
        this.targetID = targetID;
        return this;
    }
}
