package edu.ucr.rp.algoritmos.proyecto.logic.service.implementation;

import edu.ucr.rp.algoritmos.proyecto.domain.CustomerDate;
import edu.ucr.rp.algoritmos.proyecto.logic.service.interfaces.Service;
import edu.ucr.rp.algoritmos.proyecto.logic.tdamethods.implementation.CustomerDateStack;

public class DateService implements Service<CustomerDate, CustomerDateStack> {
    @Override
    public boolean add(CustomerDate element) {
        return false;
    }

    @Override
    public boolean edit(CustomerDate oldElement, CustomerDate newElement) {
        return false;
    }

    @Override
    public boolean remove(CustomerDate element) {
        return false;
    }

    @Override
    public CustomerDate getById(int iD) {
        return null;
    }

    @Override
    public CustomerDateStack getAll() {
        return null;
    }
}
