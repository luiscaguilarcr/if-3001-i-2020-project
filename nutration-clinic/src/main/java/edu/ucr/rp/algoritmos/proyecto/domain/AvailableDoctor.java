package edu.ucr.rp.algoritmos.proyecto.domain;

import java.util.Date;
import java.util.List;

public class AvailableDoctor {
    private User doctor;
    private List availableHour;
    private List<Date> availableDay;

    public User getDoctor() {
        return doctor;
    }

    public AvailableDoctor setDoctor(User doctor) {
        this.doctor = doctor;
        return this;
    }

    public List getAvailableHour() {
        return availableHour;
    }

    public AvailableDoctor setAvailableHour(List availableHour) {
        this.availableHour = availableHour;
        return this;
    }

    public List<Date> getAvailableDay() {
        return availableDay;
    }

    public AvailableDoctor setAvailableDay(List<Date> availableDay) {
        this.availableDay = availableDay;
        return this;
    }
}
