package edu.ucr.rp.algoritmos.proyecto.logic.tdamethods.interfaces;

import edu.ucr.rp.algoritmos.proyecto.logic.domain.AdminAnnotation;
import edu.ucr.rp.algoritmos.proyecto.logic.domain.CustomerDate;
import edu.ucr.rp.algoritmos.proyecto.logic.tdamethods.implementation.AdminAnnotationQueue;

public interface QueueInterface {
    void enqueue(AdminAnnotation adminAnnotation);

    AdminAnnotation dequeue();

    int size();

    AdminAnnotationQueue getByID(int iD);

    AdminAnnotation get(int index);

    boolean contains(AdminAnnotation adminAnnotation);

    boolean validateEmpty();
}
