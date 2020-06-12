package edu.ucr.rp.algoritmos.proyecto.logic.lists.implementation;

import edu.ucr.rp.algoritmos.proyecto.domain.User;
import edu.ucr.rp.algoritmos.proyecto.logic.lists.Service;

import java.util.Date;

public class Queue implements Service<Date, Queue> {

    @Override
    public boolean add(Date date) {
        return false;
    }

    @Override
    public boolean edit(Date date) {
        return false;
    }

    @Override
    public boolean remove(Date date) {
        return false;
    }

    @Override
    public Date getById(int iD) {
        return null;
    }

    @Override
    public Queue getAll() {
        return null;
    }

    private boolean validateAddition(Date date) {
        return true;
    }

    private boolean validateEdition(Date date) {
        return true;
    }
}
