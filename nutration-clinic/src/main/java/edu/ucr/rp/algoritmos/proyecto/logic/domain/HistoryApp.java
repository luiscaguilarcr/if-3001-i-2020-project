package edu.ucr.rp.algoritmos.proyecto.logic.domain;

import java.util.Date;

public class HistoryApp {
    private int userID;
    private Date date;
    private String action;

    public int getUserID() {
        return userID;
    }

    public HistoryApp setUserID(int userID) {
        this.userID = userID;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public HistoryApp setDate(Date date) {
        this.date = date;
        return this;
    }

    public String getAction() {
        return action;
    }

    public HistoryApp setAction(String action) {
        this.action = action;
        return this;
    }
}
