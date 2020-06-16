package edu.ucr.rp.algoritmos.proyecto.logic.service.implementation;

import edu.ucr.rp.algoritmos.proyecto.domain.EatingPlan;
import edu.ucr.rp.algoritmos.proyecto.logic.service.interfaces.Service;

import java.util.List;

public class EatingPlanService implements Service<EatingPlan, List> {
    @Override
    public boolean add(EatingPlan element) {
        return false;
    }

    @Override
    public boolean edit(EatingPlan oldElement, EatingPlan newElement) {
        return false;
    }

    @Override
    public boolean remove(EatingPlan element) {
        return false;
    }

    @Override
    public EatingPlan getById(int iD) {
        return null;
    }

    @Override
    public List getAll() {
        return null;
    }
}
