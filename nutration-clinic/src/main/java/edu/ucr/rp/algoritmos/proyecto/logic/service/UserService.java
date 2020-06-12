package edu.ucr.rp.algoritmos.proyecto.logic.service;

import edu.ucr.rp.algoritmos.proyecto.domain.User;
import edu.ucr.rp.algoritmos.proyecto.logic.lists.Service;
import edu.ucr.rp.algoritmos.proyecto.logic.lists.implementation.LinkedList;

import java.util.List;

/**
 * Esta clase maneja junto con la persistencia del archivo a todos los usuarios registrados
 */
public class UserService implements Service<User, LinkedList> {
    private LinkedList list = new LinkedList(); //TODO leerlo de persistencia

    @Override
    public boolean add(User element) {
        if (validateAddition(element)) {
            list.add(element);
            //TODO persistir en el archivo
            return true;
        }
        return false;
    }

    @Override
    public boolean edit(User element) {
        if(validateEdition(element)){
            list.remove(element);
            list.add(element);
            //TODO persistir en el archivo
        }
        return false;
    }

    @Override
    public boolean remove(User element) {
        if(list.contains(element)){
            list.remove(element);
            //TODO persistir la nueva lista sin el elemento
            return true;
        }
        return false;
    }

    @Override
    public User getById(int iD) {
        for (int i = 0; i < list.size() ; i++) {
            if(list.getAt(i).getiD() == iD){
                return list.getAt(i);
            }
        }
        return null;
    }

    @Override
    public LinkedList getAll() {
        return list;
    }

    private boolean validateAddition(User user) {
        if(user == null) return false;
        if (list.contains(user)) return false;
        return true;
    }

    private boolean validateEdition(User user) {
        if (!list.contains(user)) return false;
        return true;
    }
}
