package edu.ucr.rp.algoritmos.proyecto.logic.lists;

import java.util.List;

public interface Service<E, L> {
    boolean add(E element);

    boolean edit(E element);

    boolean remove(E element);

    E getById(int iD);

    L getAll(); //TODO cambiar por LinkedList
}
