package edu.ucr.rp.algoritmos.proyecto.logic.lists.interfaces;

import edu.ucr.rp.algoritmos.proyecto.domain.User;

public interface LinkedListInterface {
    void add(User user);

    void remove(User user);

    int size();

    int indexOf(User user);

    Object getAt(int index);

    boolean contains(User user);

    boolean isEmpty();

}
