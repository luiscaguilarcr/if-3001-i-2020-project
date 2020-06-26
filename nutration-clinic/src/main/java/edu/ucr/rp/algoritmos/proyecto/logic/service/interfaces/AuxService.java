package edu.ucr.rp.algoritmos.proyecto.logic.service.interfaces;

import java.util.List;

public interface AuxService<E> {
    boolean add(E e);

    boolean remove(E e);

    List getByID(int iD);
}
