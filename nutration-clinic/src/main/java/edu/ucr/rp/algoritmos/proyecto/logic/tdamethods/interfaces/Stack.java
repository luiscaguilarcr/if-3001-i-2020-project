package edu.ucr.rp.algoritmos.proyecto.logic.tdamethods.interfaces;

import edu.ucr.rp.algoritmos.proyecto.logic.domain.CustomerDate;

/**
 * @author Luis Carlos
 */
public interface Stack extends  TDA{
    void push(CustomerDate customerDate); // apila un elemento en el tope de la pila

    Object pop(); //desapila el elemento del tope de la pila y lo retorna
}
