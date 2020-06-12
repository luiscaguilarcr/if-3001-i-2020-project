package edu.ucr.rp.algoritmos.proyecto.logic.lists.interfaces;

import edu.ucr.rp.algoritmos.proyecto.domain.CustomerDate;
import edu.ucr.rp.algoritmos.proyecto.logic.lists.implementation.AdminAnnotationQueue;

public interface QueueInterface {
    void enqueue(CustomerDate adminAnotation);

    void dequeue(CustomerDate adminAnotation);

    boolean isEmpty();

    int size();

    AdminAnnotationQueue getByID(int iD);
}
