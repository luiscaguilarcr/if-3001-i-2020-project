package edu.ucr.rp.algoritmos.proyecto.logic.service.interfaces;

public interface Service<E, L> {
    boolean add(E element);

    boolean edit(E element);

    boolean remove(E element);

    E getById(int iD);

    L getAll();
}
