package edu.ucr.rp.algoritmos.proyecto.domain;

public class DateCustomer {
    private String date;
    private String hour;
    private int clientID, docID;

    public String getDate() {
        return date;
    }

    public DateCustomer setDate(String date) {
        this.date = date;
        return this;
    }

    public String getHour() {
        return hour;
    }

    public DateCustomer setHour(String hour) {
        this.hour = hour;
        return this;
    }

    public int getClientID() {
        return clientID;
    }

    public DateCustomer setClientID(int clientID) {
        this.clientID = clientID;
        return this;
    }

    public int getDocID() {
        return docID;
    }

    public DateCustomer setDocID(int docID) {
        this.docID = docID;
        return this;
    }
}
