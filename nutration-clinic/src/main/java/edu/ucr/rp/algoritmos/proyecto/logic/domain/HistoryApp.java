package edu.ucr.rp.algoritmos.proyecto.logic.domain;

public class HistoryApp {
    private int userID, targetID;
    private String date, info, hour;

    public int getUserID() {
        return userID;
    }

    public HistoryApp setUserID(int userID) {
        this.userID = userID;
        return this;
    }

    public String getDate() {
        return date;
    }

    public HistoryApp setDate(String date) {
        this.date = date;
        return this;
    }

    public String getInfo() {
        return info;
    }

    public HistoryApp setInfo(String info) {
        this.info = info;
        return this;
    }

    public String getHour() {
        return hour;
    }

    public HistoryApp setHour(String hour) {
        this.hour = hour;
        return this;
    }

    public int getTargetID() {
        return targetID;
    }

    public HistoryApp setTargetID(int targetID) {
        this.targetID = targetID;
        return this;
    }
}
