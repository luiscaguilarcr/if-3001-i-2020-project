package edu.ucr.rp.algoritmos.proyecto.logic.service.interfaces;

public interface PlanService<E> extends Service<E> {
    E getPlanByFat(int fat);

    E getByPlanID(int planID);

    Object getAll();
}
