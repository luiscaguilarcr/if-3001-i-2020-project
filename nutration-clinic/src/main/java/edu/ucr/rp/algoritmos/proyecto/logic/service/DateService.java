package edu.ucr.rp.algoritmos.proyecto.logic.service;

import edu.ucr.rp.algoritmos.proyecto.domain.DateCustomer;
import edu.ucr.rp.algoritmos.proyecto.logic.lists.Service;

import java.util.List;

public class DateService implements Service<DateCustomer, String> {
    @Override
    public boolean add(DateCustomer element) {
        return false;
    }

    @Override
    public boolean edit(DateCustomer element) {
        return false;
    }

    @Override
    public boolean remove(DateCustomer element) {
        return false;
    }

    @Override
    public DateCustomer getById(String key) {
        return null;
    }

    @Override
    public List getAll() {
        return null;
    }
}
