package edu.ucr.rp.algoritmos.proyecto.persistance.implementation;

import edu.ucr.rp.algoritmos.proyecto.domain.CustomerDate;
import edu.ucr.rp.algoritmos.proyecto.logic.tdamethods.implementation.CustomerDateStack;
import edu.ucr.rp.algoritmos.proyecto.persistance.interfaces.Persistence;

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
