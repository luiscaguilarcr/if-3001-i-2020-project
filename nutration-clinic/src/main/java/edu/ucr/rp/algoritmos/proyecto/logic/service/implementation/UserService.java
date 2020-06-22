package edu.ucr.rp.algoritmos.proyecto.logic.service.implementation;

import edu.ucr.rp.algoritmos.proyecto.logic.domain.User;
import edu.ucr.rp.algoritmos.proyecto.logic.persistance.implementation.UserPersistence;
import edu.ucr.rp.algoritmos.proyecto.logic.service.interfaces.Service;
import edu.ucr.rp.algoritmos.proyecto.logic.tdamethods.implementation.UserLinkedList;

/**
 * Esta clase maneja en conjunto con la persistencia los usuarios del sistema.
 *
 * @author Luis Carlos
 */

public class UserService implements Service<User, UserLinkedList> {
    public UserLinkedList userLinkedList;
    private UserPersistence userPersistence;
    private static UserService instance;

    /**
     * Constructor
     */
    private UserService() {
        userLinkedList = new UserLinkedList();
        userPersistence = new UserPersistence();
        refresh();
    }

    /**
     * Singleton Pattern
     */
    public static UserService getInstance() {
        if (instance == null)
            instance = new UserService();
        return instance;
    }

    /**
     * Para agregar un usuario.
     *
     * @param user que se quiere agregar
     * @return true si se agregó el usuario, si no, false
     */
    @Override
    public boolean add(User user) {
        refresh();
        if (validateAddition(user)) {
            userLinkedList.add(user);
            return userPersistence.write(userLinkedList);
        }
        return false;
    }

    /**
     * Para editar un usuario.
     *
     * @param oldUser usuario que se quiere editar
     * @param newUser usuario editado
     * @return true si el usuario se editó, si no, false
     */
    @Override
    public boolean edit(User oldUser, User newUser) {
        refresh();
        if (userLinkedList.containsByID(oldUser)) {
            userLinkedList.remove(oldUser);
            userLinkedList.add(newUser);
            userPersistence.write(userLinkedList);
            refresh();
        }
        return userLinkedList.containsByID(newUser);
    }

    /**
     * Para remover un usuario.
     *
     * @param user que se quiere remover
     * @return true si el usuario se removió, si no, false
     */
    @Override
    public boolean remove(User user) {
        refresh();
        if (userLinkedList.containsByID(user)) {
            userLinkedList.remove(user);
            return userPersistence.write(userLinkedList);
        }
        return false;
    }

    /**
     * Para obtener un usuario a partir de un ID.
     *
     * @param iD de un usuario
     * @return true si el iD ingresado corresponde a un usuario, si no, false
     */
    @Override
    public User getByID(int iD) {
        refresh();
        for (int i = 0; i < userLinkedList.size(); i++) {
            if (userLinkedList.get(i).getID() == iD) {
                return userLinkedList.get(i);
            }
        }
        return null;
    }

    public User getByName(String name) {
        refresh();
        for (int i = 0; i < userLinkedList.size(); i++) {
            if (userLinkedList.get(i).getName() == name) {
                return userLinkedList.get(i);
            }
        }
        return null;
    }

    @Override
    public UserLinkedList getAll() {
        refresh();
        return userLinkedList;
    }

    /**
     * Refresca la lista de usuarios
     */
    private void refresh() {
        userPersistence = new UserPersistence();
        UserLinkedList tempUserLinkedList = new UserLinkedList();

        userLinkedList = userPersistence.read();
        if (userLinkedList != null) {
            for (int i = 0; i < userLinkedList.size(); i++) {
                tempUserLinkedList.add(userLinkedList.get(i));
            }
        }
        userLinkedList = tempUserLinkedList;
    }

    private boolean validateAddition(User user){
        if(userLinkedList.containsByID(user)) return false;
        if(userLinkedList.containsByName(user)) return false;
        return true;
    }
}
