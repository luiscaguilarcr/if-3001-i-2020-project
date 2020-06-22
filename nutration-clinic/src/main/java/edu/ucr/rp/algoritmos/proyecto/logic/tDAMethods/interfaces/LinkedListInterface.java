package edu.ucr.rp.algoritmos.proyecto.logic.tdamethods.interfaces;

import edu.ucr.rp.algoritmos.proyecto.logic.domain.User;

public interface LinkedListInterface {
    void add(User user);

    void remove(User user);

    int size();

    int indexOf(User user);

    User get(int index);

    boolean containsByID(User user);

    boolean validateEmpty();
}
