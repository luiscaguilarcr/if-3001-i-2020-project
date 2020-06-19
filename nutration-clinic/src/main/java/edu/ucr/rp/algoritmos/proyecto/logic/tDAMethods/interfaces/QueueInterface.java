package edu.ucr.rp.algoritmos.proyecto.logic.tDAMethods.interfaces;

import edu.ucr.rp.algoritmos.proyecto.domain.CustomerDate;
import edu.ucr.rp.algoritmos.proyecto.logic.tDAMethods.implementation.AdminAnnotationQueue;

public interface QueueInterface {
    void enqueue(CustomerDate adminAnotation);

    void dequeue(CustomerDate adminAnotation);

    boolean isEmpty();

    int size();

    AdminAnnotationQueue getByID(int iD);
}
