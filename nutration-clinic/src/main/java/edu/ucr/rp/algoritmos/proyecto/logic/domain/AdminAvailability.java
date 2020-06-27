package edu.ucr.rp.algoritmos.proyecto.logic.domain;

import java.util.List;
import java.util.Map;

public class AdminAvailability {
    private int adminID;
    private Map<String, List> adminAvailability;

    public AdminAvailability() {
    }

    public int getAdminID() {
        return adminID;
    }

    public AdminAvailability setAdminID(int adminID) {
        this.adminID = adminID;
        return this;
    }

    public Map<String, List> getAdminAvailability() {
        return adminAvailability;
    }

    public AdminAvailability setAdminAvailability(Map<String, List> adminAvailability) {
        this.adminAvailability = adminAvailability;
        return this;
    }
}
