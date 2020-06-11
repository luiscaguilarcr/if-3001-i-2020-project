package edu.ucr.rp.algoritmos.proyecto.logic;

import java.util.List;

public interface Service<E, K> {
    boolean add(E element);

    boolean edit(E element);

    boolean remove(E element);

    E get(K key);

    List getAll(); //TODO cambiar por LinkedList
}
