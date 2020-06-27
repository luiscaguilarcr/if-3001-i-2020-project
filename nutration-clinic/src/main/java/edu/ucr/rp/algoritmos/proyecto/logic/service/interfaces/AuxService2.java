package edu.ucr.rp.algoritmos.proyecto.logic.service.interfaces;

public interface AuxService2<E, L> {
    boolean add(E element);

    boolean edit(E oldElement, E newElement);

    boolean remove(E element);

    E getByDateAndCustomerID(String date, int customerID);

    L getAllByID(int iD);
}
