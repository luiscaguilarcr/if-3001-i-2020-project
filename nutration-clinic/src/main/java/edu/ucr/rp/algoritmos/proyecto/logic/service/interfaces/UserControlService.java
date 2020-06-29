package edu.ucr.rp.algoritmos.proyecto.logic.service.interfaces;

import java.util.List;

public interface UserControlService<E> extends Service<E> {

    boolean edit(E oldElement, E newElement);

    Object getByID(int iD);

    Object getByName(String name);

    Object getAdminNames();

    Object getCustomerNames();

    Object getUserNames();

    Object getAll();
}
