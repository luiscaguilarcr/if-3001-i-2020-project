package edu.ucr.rp.algoritmos.proyecto.logic.service.implementation;

import edu.ucr.rp.algoritmos.proyecto.logic.domain.CustomerDate;
import edu.ucr.rp.algoritmos.proyecto.logic.persistance.implementation.DatePersistence;
import edu.ucr.rp.algoritmos.proyecto.logic.service.interfaces.Service;
import edu.ucr.rp.algoritmos.proyecto.logic.tdamethods.implementation.AdminAnnotationQueue;
import edu.ucr.rp.algoritmos.proyecto.logic.tdamethods.implementation.CustomerDateStack;

import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase maneja en conjunto con la persistencia, los TDA(stack) y los objetos tipo CustomerDate las citas
 * registradas en del sistema.
 *
 * @author Luis Carlos Aguilar
 */
public class DateService implements Service<CustomerDate, CustomerDateStack> {
    public CustomerDateStack stack;
    private DatePersistence datePersistence;
    private static DateService instance;

    /**
     * Constructor
     */
    private DateService() {
        stack = new CustomerDateStack();
        datePersistence = new DatePersistence();
        refresh();
    }

    /**
     * Singleton Pattern
     */
    public static DateService getInstance() {
        if (instance == null)
            instance = new DateService();
        return instance;
    }

    /**
     * Para agregar una cita.
     *
     * @param customerDate que se quiere agregar
     * @return true si la cita fue agregada, si no, false
     */
    @Override
    public boolean add(CustomerDate customerDate) {
        refresh();
        if (!stack.contains(customerDate)) {
            stack.push(customerDate);
            return datePersistence.write(stack);
        }
        return false;
    }

    /**
     * Para editar una cita.
     *
     * @param oldCustomerDate cita que se quiere editar
     * @param newCustomerDate cita editada
     * @return true si la cita fue editada, si no, false
     */
    @Override
    public boolean edit(CustomerDate oldCustomerDate, CustomerDate newCustomerDate) {
        refresh();
        if (stack.contains(oldCustomerDate)) {
            stack.pop(oldCustomerDate);
            stack.push(newCustomerDate);
            datePersistence.write(stack);
            refresh();
        }
        return stack.contains(newCustomerDate);
    }

    /**
     * Para remover una cita.
     *
     * @param customerDate que se quiere remover
     * @return true si la cita fue removida, si no, false
     */
    @Override
    public boolean remove(CustomerDate customerDate) {
        refresh();
        if (stack.contains(customerDate)) {
            stack.pop(customerDate);
            return datePersistence.write(stack);
        }
        return false;
    }

    /**
     * Para obtener una cita a partir de un ID de usuario.
     *
     * @param iD de un usuario
     * @return true si el iD ingresado corresponde a una cita, si no, false
     */
    @Override
    public CustomerDate getByID(int iD) {
        refresh();
        for (int i = 0; i < stack.size()-1; i++) {
            if (stack.getByAcc(i).getCustomerID() == iD) {
                return stack.getByAcc(i);
            }
            if (stack.getByAcc(i).getAdminID() == iD) {
                return null;
            }
        }
        return null;
    }

    /**
     * Obtiene una pila de citas que estén a nombre de un administrador.
     * @param adminID
     * @return
     */
    public CustomerDateStack getDatesByAdminID(int adminID) {
        refresh();
        CustomerDateStack tempCustomerDateStack = new CustomerDateStack();
        for (int i = 0; i < stack.size(); i++) {
            if (stack.getByAcc(i).getCustomerID() == adminID) {
                return null;
            }
            if (stack.getByAcc(i).getAdminID() == adminID) {
                tempCustomerDateStack.push(stack.getByAcc(i));
            }
        }
        return tempCustomerDateStack;
    }

    /**
     * Obtiene todas las citas registradas en el sistema.
     * @return pila de citas.
     */
    @Override
    public CustomerDateStack getAll() {
        refresh();
        return stack;
    }

    public List getNamesOfCustomersByDates(CustomerDateStack customerDateStack){
        List customerNamesByDates = new ArrayList();
        UserService userService = UserService.getInstance();
        for (int i = 0; i < customerDateStack.size(); i++) {
            String name = userService.getByID(customerDateStack.getByAcc(i).getCustomerID()).getName();
            customerNamesByDates.add(name);
        }
        return customerNamesByDates;
    }

    /**
     * Refresca la lista de citas
     */
    private void refresh() {
        //Lee el archivo
        Object object = datePersistence.read();
        //Valida que existe y lo sustituye por la lista en memoria
        if (object != null) {
            stack = (CustomerDateStack) object;
        }
    }
}
