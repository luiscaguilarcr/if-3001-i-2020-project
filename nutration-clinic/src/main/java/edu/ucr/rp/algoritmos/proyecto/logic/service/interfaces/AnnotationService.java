package edu.ucr.rp.algoritmos.proyecto.logic.service.interfaces;

public interface AnnotationService<E> {
    boolean add(E element);

    void refresh();

    Object getByDateAndCustomerID(String date, int customerID);

    Object getAllByID(int iD);
}
