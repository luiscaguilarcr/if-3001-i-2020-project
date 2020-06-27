package edu.ucr.rp.algoritmos.proyecto.logic.domain;

import java.util.List;

public class AdminAvailability {
    private User doctor;
    private List availableHour;
    private List<String> notAvailableDay;

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

    public List<String> getAvailableDay() {
        return notAvailableDay;
    }

    public AdminAvailability setAvailableDay(List<String> notAvailableDay) {
        this.notAvailableDay = notAvailableDay;
        return this;
    }
}
