package edu.ucr.rp.algoritmos.proyecto.persistance.implementation;

import edu.ucr.rp.algoritmos.proyecto.domain.EatingPlan;
import edu.ucr.rp.algoritmos.proyecto.persistance.interfaces.Persistence;

import java.util.List;

public class EatingPlanPersistence implements Persistence<EatingPlan, List> {

    @Override
    public boolean write(List list) {
        return false;
    }

    @Override
    public List read() {
        return null;
    }

    @Override
    public boolean deleteAll() {
        return false;
    }
}
