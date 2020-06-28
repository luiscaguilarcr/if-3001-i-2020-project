package edu.ucr.rp.algoritmos.proyecto.logic.tdamethods.interfaces;

import edu.ucr.rp.algoritmos.proyecto.logic.domain.HistoryApp;

/**
 * @author Luis Carlos
 */
public interface AVLTree extends TDA {
    void add(HistoryApp element); //inserta un elemento en el árbol

    void remove(HistoryApp element); //suprime un elemento del árbol

    Object get(int index); //true si el elemento existe en el árbol//private boolean binarySearch(BTreeNode node, Object element)

}
