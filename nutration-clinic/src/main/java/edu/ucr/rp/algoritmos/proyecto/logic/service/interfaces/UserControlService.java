package edu.ucr.rp.algoritmos.proyecto.logic.service.interfaces;

public interface UserControlService<E> extends Service<E> {

    boolean edit(E oldElement, E newElement);

    E getByID(int iD);

    E getByName(String name);

    Object getAdminNames();

    Object getCustomerNames();

    Object getAll();
}
