package edu.ucr.rp.algoritmos.proyecto.logic.service.implementation;

import edu.ucr.rp.algoritmos.proyecto.domain.AvailableDoctor;
import edu.ucr.rp.algoritmos.proyecto.logic.service.interfaces.Service;

import java.util.List;

public class AvailableDoctorsService implements Service <AvailableDoctor, List> {

    @Override
    public boolean add(AvailableDoctor element) {
        return false;
    }

    @Override
    public boolean edit(AvailableDoctor oldElement, AvailableDoctor newElement) {
        return false;
    }

    @Override
    public boolean remove(AvailableDoctor element) {
        return false;
    }

    @Override
    public AvailableDoctor getById(int iD) {
        return null;
    }

    @Override
    public List getAll() {
        return null;
    }
}
