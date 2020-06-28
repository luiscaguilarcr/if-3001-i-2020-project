package edu.ucr.rp.algoritmos.proyecto.logic.service.implementation;

import edu.ucr.rp.algoritmos.proyecto.logic.domain.AdminAvailability;
import edu.ucr.rp.algoritmos.proyecto.logic.domain.User;
import edu.ucr.rp.algoritmos.proyecto.logic.persistance.implementation.UserPersistence;
import edu.ucr.rp.algoritmos.proyecto.logic.service.interfaces.UserControlService;
import edu.ucr.rp.algoritmos.proyecto.logic.tdamethods.implementation.UserLinkedList;
import edu.ucr.rp.algoritmos.proyecto.util.test.TestUtility;
import edu.ucr.rp.algoritmos.proyecto.util.Utility;

import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase maneja en conjunto con la persistencia, los TDA(linked list) y los objetos tipo User
 * a los usuarios en el sistema.
 *
 * @author Luis Carlos Aguilar
 */
public class UserService implements UserControlService<User> {
    public UserLinkedList userLinkedList;
    private UserPersistence userPersistence;
    private Utility utility;
    private static UserService instance;

    /**
     * Constructor
     */
    private UserService() {
        userLinkedList = new UserLinkedList();
        userPersistence = new UserPersistence();
        utility = new Utility();
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
     * @return true si el usuario fue agregado, si no, false
     */
    @Override
    public boolean add(User user) {
        refresh();
        if (validateAddition(user)) {
            userLinkedList.add(user);
            userPersistence.write(userLinkedList);
            refresh();
            if (user.getRol() == 2) {
                addAdminAvailability(user.getID());
            }
            //utility.historyApp("El usuario con ID: "+user.getID()+" fue agregado");
            return true;
        }
        return false;
    }

    /**
     * Para editar un usuario.
     *
     * @param oldUser usuario que se quiere editar
     * @param newUser usuario editado
     * @return true si el usuario fue editado, si no, false
     */
    @Override
    public boolean edit(User oldUser, User newUser) {
        refresh();
        if (userLinkedList.containsByID(oldUser)) {
            userLinkedList.remove(oldUser);
            userLinkedList.add(newUser);
            userPersistence.write(userLinkedList);
            utility.historyApp("El usuario con ID: " + oldUser.getID() + " fue editado");
            refresh();
        }
        return userLinkedList.containsByID(newUser);
    }

    /**
     * Para remover un usuario.
     *
     * @param user que se quiere remover
     * @return true si el usuario fue removido, si no, false
     */
    @Override
    public boolean remove(User user) {
        refresh();
        if (userLinkedList.containsByID(user)) {
            userLinkedList.remove(user);
            utility.removeCustomerDate(user.getID());
            //utility.historyApp("El usuario con ID: "+user.getID()+" fue removido");
            if (user.getRol() == 2) {
                deleteAdminAvailability(user.getID());
            }
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

    @Override
    public User getByName(String name) {
        refresh();
        for (int i = 0; i < userLinkedList.size(); i++) {
            if (userLinkedList.get(i).getName().equals(name)) {
                return userLinkedList.get(i);
            }
        }
        return null;
    }

    @Override
    public List<String> getAdminNames(){
        refresh();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < userLinkedList.size(); i++) {
            if(userLinkedList.get(i).getRol() == 2){
                list.add(userLinkedList.get(i).getName());
            }
        }
        return list;
    }

    @Override
    public List<String> getCustomerNames() {
        refresh();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < userLinkedList.size(); i++) {
            if(userLinkedList.get(i).getRol() == 3){
                list.add(userLinkedList.get(i).getName());
            }
        }
        return list;
    }

    @Override
    public UserLinkedList getAll() {
        refresh();
        return userLinkedList;
    }

    /**
     * Refresca la lista de usuarios.
     */
    @Override
    public void refresh() {
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

    /**
     * Valida si un usuario puede ser agregado.
     *
     * @param user que se quiere agregar
     * @return true si se puede agregar el usuario, si no, fase
     */
    private boolean validateAddition(User user) {
        if (userLinkedList.containsByID(user)) return false;
        if (userLinkedList.containsByName(user)) return false;
        return true;
    }

    private void addAdminAvailability(int iD) { //TODO test
        AdminAvailabilityGeneralService adminAvailabilityService = AdminAvailabilityGeneralService.getInstance();
        TestUtility testUtility = new TestUtility();
        AdminAvailability adminAvailability = testUtility.generateAdminAvailability(iD);
        adminAvailabilityService.add(adminAvailability);
    }

    private void deleteAdminAvailability(int iD) { //TODO test
        AdminAvailabilityGeneralService adminAvailabilityService = AdminAvailabilityGeneralService.getInstance();
        AdminAvailability adminAvailability = adminAvailabilityService.getByID(iD);
        adminAvailabilityService.remove(adminAvailability);
    }

}
