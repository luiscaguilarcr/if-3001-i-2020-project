package edu.ucr.rp.algoritmos.proyecto.logic.domain;

import java.util.Date;
import java.util.List;

public class AdminAvailability {
    private User doctor;
    private List availableHour;
    private List<Date> availableDay;

    public User getDoctor() {
        return doctor;
    }

    public AdminAvailability setDoctor(User doctor) {
        this.doctor = doctor;
        return this;
    }

    public List getAvailableHour() {
        return availableHour;
    }

    public AdminAvailability setAvailableHour(List availableHour) {
        this.availableHour = availableHour;
        return this;
    }

    public List<Date> getAvailableDay() {
        return availableDay;
    }

    public AdminAvailability setAvailableDay(List<Date> availableDay) {
        this.availableDay = availableDay;
        return this;
    }
}
