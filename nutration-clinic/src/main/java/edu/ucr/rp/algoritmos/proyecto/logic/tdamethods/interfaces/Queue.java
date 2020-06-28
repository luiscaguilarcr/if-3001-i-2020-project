package edu.ucr.rp.algoritmos.proyecto.logic.tdamethods.interfaces;

import edu.ucr.rp.algoritmos.proyecto.logic.domain.AdminAnnotation;

/**
 * @author Luis Carlos
 */
public interface Queue extends TDA {
    void enQueue(AdminAnnotation element); // encola un elemento por el extremo posterior(final) de la cola

    AdminAnnotation deQueue(); //suprime y retorna el elemento que está en la parte anterior(frente/inicio) de la cola

    boolean contains(AdminAnnotation element); //true si el elemento fue encolado

    AdminAnnotation front(); //obtiene el primer de la cola
}
