package edu.ucr.rp.algoritmos.proyecto.logic.service.interfaces;

public interface Service<E> {
    boolean add(E element);

    boolean remove(E element);

    void refresh();
}
