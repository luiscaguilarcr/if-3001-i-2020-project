package edu.ucr.rp.algoritmos.proyecto.persistance.implementation;

import edu.ucr.rp.algoritmos.proyecto.domain.AdminAnnotation;
import edu.ucr.rp.algoritmos.proyecto.logic.tdamethods.implementation.AdminAnnotationQueue;
import edu.ucr.rp.algoritmos.proyecto.persistance.interfaces.Persistence;

public class AnnotationPersistence implements Persistence<AdminAnnotation, AdminAnnotationQueue> {

    @Override
    public boolean write(AdminAnnotationQueue adminAnnotationQueue) {
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
