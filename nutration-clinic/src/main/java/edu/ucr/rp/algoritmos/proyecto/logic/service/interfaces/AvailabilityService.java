package edu.ucr.rp.algoritmos.proyecto.logic.service.interfaces;

public interface AvailabilityService<E> extends Service<E>{
    boolean edit(E oldElement, E newElement);

    E getByID(int iD);

    Object getAll();
}
