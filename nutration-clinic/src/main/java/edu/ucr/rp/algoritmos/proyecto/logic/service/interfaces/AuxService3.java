package edu.ucr.rp.algoritmos.proyecto.logic.service.interfaces;

public interface AuxService3<E, L> {
    boolean add(E element);

    boolean remove(E element);

    L getAll();
}
