package edu.ucr.rp.algoritmos.proyecto.logic.service.implementation;

import edu.ucr.rp.algoritmos.proyecto.domain.User;
import edu.ucr.rp.algoritmos.proyecto.logic.service.interfaces.Service;
import edu.ucr.rp.algoritmos.proyecto.logic.lists.implementation.UserLinkedList;
import edu.ucr.rp.algoritmos.proyecto.persistance.UserPersistence;

/**
 * Esta clase maneja junto con la persistencia del archivo a todos los usuarios registrados
 */
public class UserService implements Service<User, UserLinkedList> {
     public UserLinkedList list = new UserLinkedList(); //TODO leerlo de persistencia
     public UserPersistence userPersistence = new UserPersistence();

    @Override
    public boolean add(User element) {
        if (validateAddition(element)) {
            list.add(element);
            userPersistence.write(element); //TODO test
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
    public UserLinkedList getAll() {
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
