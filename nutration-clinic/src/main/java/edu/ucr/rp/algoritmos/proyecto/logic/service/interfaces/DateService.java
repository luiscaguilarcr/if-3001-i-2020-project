package edu.ucr.rp.algoritmos.proyecto.logic.service.interfaces;

public interface DateService<E> extends Service<E> {
    boolean edit(E oldElement, E newElement);

    E getByID(int iD);

    Object getAll();
}
