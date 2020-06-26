package edu.ucr.rp.algoritmos.proyecto.logic.service.interfaces;

public interface AuxService4<E, L> {
    boolean add(E element);

    boolean remove(E element);

    boolean edit(E oldElement, E newElement);

    L getAll();
}
