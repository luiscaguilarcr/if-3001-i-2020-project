package edu.ucr.rp.algoritmos.proyecto.logic.tdamethods.interfaces;

import edu.ucr.rp.algoritmos.proyecto.logic.domain.User;

/**
 * @author Luis Carlos
 */
public interface LinkedListInterface extends TDA {
    void add(User user);

    void remove(User user);

    boolean containsByID(User user);

    Object get(int index); //true si el elemento existe en el árbol//private boolean binarySearch(BTreeNode node, Object element)
}
