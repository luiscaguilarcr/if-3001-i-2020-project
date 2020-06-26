package edu.ucr.rp.algoritmos.proyecto.logic.service.implementation;

import edu.ucr.rp.algoritmos.proyecto.logic.domain.AdminAvailability;
import edu.ucr.rp.algoritmos.proyecto.logic.domain.EatingPlan;
import edu.ucr.rp.algoritmos.proyecto.logic.persistance.implementation.AdminAvailabilityPersistence;
import edu.ucr.rp.algoritmos.proyecto.logic.persistance.implementation.EatingPlanPersistence;
import edu.ucr.rp.algoritmos.proyecto.logic.service.interfaces.AuxService4;
import edu.ucr.rp.algoritmos.proyecto.logic.service.interfaces.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase maneja en conjunto con la persistencia y los objetos tipo AdminAvailability la disponibilidad de
 * un administrador del sistema.
 *
 * @author Luis Carlos Aguilar
 */
public class AdminAvailableService implements AuxService4 <AdminAvailability, List> {
    public List<AdminAvailability> list;
    private AdminAvailabilityPersistence adminAvailabilityPersistence;
    private static AdminAvailableService instance;

    /**
     * Constructor
     */
    private AdminAvailableService() {
        list = new ArrayList();
        adminAvailabilityPersistence = new AdminAvailabilityPersistence();
        refresh();
    }

    /**
     * Singleton Pattern
     */
    public static AdminAvailableService getInstance() {
        if (instance == null)
            instance = new AdminAvailableService();
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
        if (list.contains(oldAdminAvailability)) {
            list.remove(oldAdminAvailability);
            list.add(newAdminAvailability);
            adminAvailabilityPersistence.write(list);
            refresh();
        }
        return list.contains(newAdminAvailability);
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
        if (list.contains(adminAvailability)) {
            list.remove(adminAvailability);
            return adminAvailabilityPersistence.write(list);
        }
        return false;
    }

    /**
     * Obtiene toda la disponibilidad de un administrador.
     * @return lista con la disponibilidad de todos los administradores
     */
    @Override
    public List getAll() {
        refresh();
        return list;
    }

    /**
     * Refresca la lista de disponibilidad de los administrador ya ingresados en el sistema.
     */
    private void refresh() {
        //Lee el archivo
        Object object = adminAvailabilityPersistence.read();
        //Valida que existe y lo sustituye por la lista en memoria
        if (object != null) {
            list = (List<AdminAvailability>) object;
        }
    }

    /**
     * Valida si se puede agregar la disponibilidad de un administrador.
     * @param adminAvailability que se quiere validar
     * @return true si se puede agregar, si no, false
     */
    private boolean validateAddition(AdminAvailability adminAvailability) {
        if (list.contains(adminAvailability)) return false;
        return true;
    }
}
