package edu.ucr.rp.algoritmos.proyecto.logic.tdamethods.interfaces;

import edu.ucr.rp.algoritmos.proyecto.logic.domain.CustomerDate;

/**
 * @author Luis Carlos
 */
public interface BTree extends TDA {
    void add(CustomerDate element); //inserta un elemento en el árbol

    void remove(CustomerDate element); //suprime un elemento del árbol

    Object get(int index); //true si el elemento existe en el árbol//private boolean binarySearch(BTreeNode node, Object element)

}
