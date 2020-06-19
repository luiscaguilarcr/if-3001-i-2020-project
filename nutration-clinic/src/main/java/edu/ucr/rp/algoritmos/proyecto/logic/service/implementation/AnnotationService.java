package edu.ucr.rp.algoritmos.proyecto.logic.service.implementation;

import edu.ucr.rp.algoritmos.proyecto.domain.AdminAnnotation;
import edu.ucr.rp.algoritmos.proyecto.logic.tDAMethods.implementation.AdminAnnotationQueue;
import edu.ucr.rp.algoritmos.proyecto.logic.service.interfaces.Service;

public class AnnotationService implements Service<AdminAnnotation, AdminAnnotationQueue> {
    @Override
    public boolean add(AdminAnnotation element) {
        return false;
    }

    @Override
    public boolean edit(AdminAnnotation oldElement, AdminAnnotation newElement) {
        return false;
    }

    @Override
    public boolean remove(AdminAnnotation element) {
        return false;
    }

    @Override
    public AdminAnnotation getById(int iD) {
        return null;
    }

    @Override
    public AdminAnnotationQueue getAll() {
        return null;
    }
}
