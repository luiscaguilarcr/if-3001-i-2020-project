package edu.ucr.rp.algoritmos.proyecto.logic.service.implementation;

import edu.ucr.rp.algoritmos.proyecto.logic.domain.AdminAvailability;
import edu.ucr.rp.algoritmos.proyecto.logic.domain.User;
import edu.ucr.rp.algoritmos.proyecto.logic.persistance.implementation.AdminAvailabilityPersistence;
import edu.ucr.rp.algoritmos.proyecto.logic.service.interfaces.AvailabilityService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Esta clase maneja en conjunto con la persistencia y los objetos tipo AdminAvailability la disponibilidad de
 * un administrador del sistema.
 *
 * @author Luis Carlos Aguilar
 */
public class AdminAvailabilityService implements AvailabilityService<AdminAvailability> {
    public List<AdminAvailability> list;
    private AdminAvailabilityPersistence adminAvailabilityPersistence;
    private UserService userService;
    private static AdminAvailabilityService instance;

    /**
     * Constructor
     */
    private AdminAvailabilityService() {
        adminAvailabilityPersistence = new AdminAvailabilityPersistence();
        userService = UserService.getInstance();
        list = new ArrayList();
        refresh();
    }

    /**
     * Singleton Pattern
     */
    public static AdminAvailabilityService getInstance() {
        if (instance == null)
            instance = new AdminAvailabilityService();
        return instance;
    }

    /**
     * Para agregar la disponibilidad de un administrador.
     *
     * @param adminAvailability que se quiere agregar
     * @return true si fue agregada la disponibilidad del administrador, si no, false
     */
    @Override
    public boolean add(AdminAvailability adminAvailability) {
        refresh();
        if (validateAddition(adminAvailability)) {
            list.add(adminAvailability);
            return adminAvailabilityPersistence.write(list);
        }
        return false;
    }

    /**
     * Para editar la disponibilidad de un administrador.
     *
     * @param oldAdminAvailability disponibilidad de un administrador que se quiere editar
     * @param newAdminAvailability disponibilidad de un administrador editada
     * @return true si la disponibilidad de un administrador fue editada, si no, false
     */
    @Override
    public boolean edit(AdminAvailability oldAdminAvailability, AdminAvailability newAdminAvailability) {
        refresh();
        int index = containsIndexOf(oldAdminAvailability.getAdminID());
        if (index != -1) {
            list.remove(index);
            list.add(newAdminAvailability);
            adminAvailabilityPersistence.write(list);
            return true;
        }
        return false;
    }

    /**
     * Para remover la disponibilidad de un administrador.
     *
     * @param adminAvailability que se quiere remover
     * @return true si la disponibilidad de un administrador fue removida, si no, false
     */
    @Override
    public boolean remove(AdminAvailability adminAvailability) {
        refresh();
        if (!list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getAdminID() == adminAvailability.getAdminID()) {
                    list.remove(i);
                }
            }
            return adminAvailabilityPersistence.write(list);
        }
        return false;
    }

    /**
     * Obtiene toda la disponibilidad de un administrador.
     *
     * @return lista con la disponibilidad de todos los administradores
     */
    @Override
    public List<AdminAvailability> getAll() {
        refresh();
        return list;
    }

    public AdminAvailability getByID(int iD) {
        refresh();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getAdminID() == iD) {
                return list.get(i);
            }
        }
        return null;
    }

    /**
     * Para obtener la disponibilidad de un doctor en un Map
     * @param iD del doctor
     * @return mapa con la disponibilidad del doctor
     */
    public Map<String, List> getByIDMapAvailability(int iD) {
        refresh();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getAdminID() == iD) {
                return list.get(i).getAdminAvailability();
            }
        }
        return null;
    }

    public boolean addHourByDate(String date, String hour, AdminAvailability adminAvailability) {
        if (contains(adminAvailability.getAdminID())) {
            AdminAvailability adminAvailability1 = adminAvailability;
            List list = adminAvailability1.getAdminAvailability().get(date);
            list.add(hour);
            edit(adminAvailability, adminAvailability1);
            return true;
        }
        return false;
    }


    public boolean deleteHourByDate(String date, String hour, AdminAvailability adminAvailability) {
        if (contains(adminAvailability.getAdminID())) {
            AdminAvailability adminAvailability1 = adminAvailability;
            List list = adminAvailability1.getAdminAvailability().get(date);
            list.remove(hour);
            edit(adminAvailability, adminAvailability1);
            return true;
        }
        return false;
    }

    public List<String> getNamesListByDate(String date) {
        refresh();
        List<String> tempList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Map<String, List> map = list.get(i).getAdminAvailability();
            if(map.containsKey(date)){
                User user = userService.getByID(list.get(i).getAdminID());
                tempList.add(user.getName());
            }
        }
        return tempList;
    }
    @Override
    public void refresh() {
        //Lee el archivo
        Object object = adminAvailabilityPersistence.read();
        //Valida que existe y lo sustituye por la lista en memoria
        if (object != null) {
            ArrayList<AdminAvailability> arrayList = (ArrayList<AdminAvailability>) object;
            list = adminAvailabilityPersistence.convert(arrayList);
        }

    }

    /**
     * Valida si se puede agregar la disponibilidad de un administrador.
     *
     * @param adminAvailability que se quiere validar
     * @return true si se puede agregar, si no, false
     */
    private boolean validateAddition(AdminAvailability adminAvailability) {
        if (list.contains(adminAvailability)) return false;
        return true;
    }

    public int containsIndexOf(int iD) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getAdminID() == iD) {
                return i;
            }
        }
        return -1;
    }

    public boolean contains(int iD) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getAdminID() == iD) {
                return true;
            }
        }
        return false;
    }
}
