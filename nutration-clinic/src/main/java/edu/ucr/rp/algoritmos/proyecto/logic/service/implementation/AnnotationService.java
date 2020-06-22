package edu.ucr.rp.algoritmos.proyecto.logic.service.implementation;

import edu.ucr.rp.algoritmos.proyecto.logic.domain.AdminAnnotation;
import edu.ucr.rp.algoritmos.proyecto.logic.tdamethods.implementation.AdminAnnotationQueue;
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
    public AdminAnnotation getByID(int iD) {
        return null;
    }

    @Override
    public AdminAnnotationQueue getAll() {
        return null;
    }
}
