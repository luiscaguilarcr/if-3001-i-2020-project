package edu.ucr.rp.algoritmos.proyecto.logic.service.implementation;

import edu.ucr.rp.algoritmos.proyecto.domain.User;
import edu.ucr.rp.algoritmos.proyecto.logic.service.interfaces.Service;
import edu.ucr.rp.algoritmos.proyecto.logic.tdamethods.implementation.UserLinkedList;
import edu.ucr.rp.algoritmos.proyecto.persistance.implementation.UserPersistence;
import edu.ucr.rp.algoritmos.proyecto.util.files.IOUtility;

/**
 * Esta clase maneja en conjunto con la persistencia los usuarios del sistema.
 *
 * @author Luis Carlos
 */

public class UserService implements Service<User, UserLinkedList> {
    public UserLinkedList list;
    private UserPersistence userPersistence;
    private static UserService instance;

    /**
     * Constructor
     */
    private UserService() {
        list = new UserLinkedList();
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
        if (!list.contains(user)) {
            list.add(user);
            return userPersistence.write(list);
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
        if (list.contains(oldUser)) {
            list.remove(oldUser);
            list.add(newUser);
            userPersistence.write(list);
            refresh();
        }
        return list.contains(newUser);
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
        if (list.contains(user)) {
            list.remove(user);
            return userPersistence.write(list);
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
    public User getById(int iD) {
        refresh();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getiD() == iD) {
                return list.get(i);
            }
        }
        return null;
    }

    @Override
    public UserLinkedList getAll() {
        refresh();
        return list;
    }

    /**
     * Refresca la lista de usuarios
     */
    private void refresh() {
        IOUtility.verifyUsersDir();
        userPersistence = new UserPersistence();
        UserLinkedList tempUserLinkedList = new UserLinkedList();
        if (userPersistence.read() != null) {
            list = userPersistence.read();
            for (int i = 0; i < list.size(); i++) {
                tempUserLinkedList.add(list.get(i));
            }
        }
        list = tempUserLinkedList;
    }
}
