package edu.ucr.rp.algoritmos.proyecto.logic.service.implementation;

import edu.ucr.rp.algoritmos.proyecto.logic.domain.AdminAvailability;
import edu.ucr.rp.algoritmos.proyecto.logic.service.interfaces.Service;

import java.util.List;

public class AvailableDoctorsService implements Service <AdminAvailability, List> {

    @Override
    public boolean add(AdminAvailability element) {
        return false;
    }

    @Override
    public boolean edit(AdminAvailability oldElement, AdminAvailability newElement) {
        return false;
    }

    @Override
    public boolean remove(AdminAvailability element) {
        return false;
    }

    @Override
    public AdminAvailability getById(int iD) {
        return null;
    }

    @Override
    public List getAll() {
        return null;
    }
}
