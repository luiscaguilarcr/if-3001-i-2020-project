package edu.ucr.rp.algoritmos.proyecto.persistance;

import edu.ucr.rp.algoritmos.proyecto.domain.CustomerDate;
import edu.ucr.rp.algoritmos.proyecto.logic.lists.implementation.CustomerDateStack;

public class DatePersistence implements Persistence<CustomerDate, CustomerDateStack> {

    @Override
    public boolean write(CustomerDateStack customerDateStack) {
        return false;
    }

    @Override
    public CustomerDateStack read() {
        return null;
    }

    @Override
    public boolean deleteAll() {
        return false;
    }
}
