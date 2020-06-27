package edu.ucr.rp.algoritmos.proyecto.logic.service.interfaces;

import java.util.List;

public interface AuxService<E, L> {
    boolean add(E e);

    boolean remove(E e);

    L getByID(int iD);
}
