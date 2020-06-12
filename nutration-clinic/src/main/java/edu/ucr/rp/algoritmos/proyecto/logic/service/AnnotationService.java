package edu.ucr.rp.algoritmos.proyecto.logic.service;

import edu.ucr.rp.algoritmos.proyecto.domain.AnnotationAdmin;
import edu.ucr.rp.algoritmos.proyecto.logic.lists.Service;

import java.util.List;

public class AnnotationService implements Service<AnnotationAdmin, String> {
    @Override
    public boolean add(AnnotationAdmin element) {
        return false;
    }

    @Override
    public boolean edit(AnnotationAdmin element) {
        return false;
    }

    @Override
    public boolean remove(AnnotationAdmin element) {
        return false;
    }

    @Override
    public AnnotationAdmin getById(String key) {
        return null;
    }

    @Override
    public List getAll() {
        return null;
    }
}
