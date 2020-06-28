package edu.ucr.rp.algoritmos.proyecto.util.files.backUp;

import edu.ucr.rp.algoritmos.proyecto.logic.domain.AdminAnnotation;

public interface QueueInterface {
    void enqueue(AdminAnnotation adminAnnotation);

    AdminAnnotation dequeue();

    int size();

    AdminAnnotationQueue getByID(int iD);

    AdminAnnotation get(int index);

    boolean contains(AdminAnnotation adminAnnotation);

    boolean validateEmpty();
}
