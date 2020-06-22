package edu.ucr.rp.algoritmos.proyecto.logic.domain;

import java.util.Date;

public class CustomerDate {
    private String date;
    private String hour;
    private int customerID, adminID, eatingPlanID;

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

    public int getEatingPlanID() {
        return eatingPlanID;
    }

    public CustomerDate setEatingPlanID(int eatingPlanID) {
        if(eatingPlanID == 1){
            this.eatingPlanID = eatingPlanID;
            return this;
        }
        return null;
    }
}
