package edu.ucr.rp.algoritmos.proyecto.domain.util;

public class Date {
    private String date;
    private String hour;
    private Annotations annotations;
    private int client;
    private int doc;

    public Date(String date, String hour, Annotations annotations) {
        this.date = date;
        this.hour = hour;
        this.annotations = annotations;
    }

    public String getDate() {
        return date;
    }

    public Date setDate(String date) {
        this.date = date;
        return this;
    }

    public String getHour() {
        return hour;
    }

    public Date setHour(String hour) {
        this.hour = hour;
        return this;
    }

    public Annotations getAnnotations() {
        return annotations;
    }

    public Date setAnnotations(Annotations annotations) {
        this.annotations = annotations;
        return this;
    }
}
