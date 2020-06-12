package edu.ucr.rp.algoritmos.proyecto.persistance;

import edu.ucr.rp.algoritmos.proyecto.domain.AdminAnnotation;
import edu.ucr.rp.algoritmos.proyecto.logic.lists.implementation.AdminAnnotationQueue;

public class AnnotationPersistence implements Persistence<AdminAnnotation, AdminAnnotationQueue> {

    @Override
    public boolean write(AdminAnnotation adminAnnotation) {
        return false;
    }

    @Override
    public AdminAnnotationQueue read() {
        return null;
    }

    @Override
    public boolean deleteAll() {
        return false;
    }

}
