package edu.ucr.rp.algoritmos.proyecto.logic.service.interfaces;

public interface AuxService5<E, L> {
    boolean add(E element);

    E getByDateAndCustomerID(String date, int customerID);

    L getAllByID(int iD);
}
