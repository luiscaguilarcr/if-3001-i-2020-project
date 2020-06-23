package edu.ucr.rp.algoritmos.proyecto.logic.service.implementation;

import edu.ucr.rp.algoritmos.proyecto.logic.domain.CustomerDate;
import edu.ucr.rp.algoritmos.proyecto.logic.domain.User;
import edu.ucr.rp.algoritmos.proyecto.logic.persistance.implementation.DatePersistence;
import edu.ucr.rp.algoritmos.proyecto.logic.service.interfaces.Service;
import edu.ucr.rp.algoritmos.proyecto.logic.tdamethods.implementation.CustomerDateStack;

import java.util.ArrayList;
import java.util.List;


public class DateService implements Service<CustomerDate, CustomerDateStack> {
    public CustomerDateStack customerDateStack;
    private DatePersistence datePersistence;
    private static DateService instance;

    /**
     * Constructor
     */
    private DateService() {
        customerDateStack = new CustomerDateStack();
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
     * @return true si se agregó la cita, si no, false
     */
    @Override
    public boolean add(CustomerDate customerDate) {
        refresh();
        if (!customerDateStack.contains(customerDate)) {
            customerDateStack.push(customerDate);
            return datePersistence.write(customerDateStack);
        }
        return false;
    }

    /**
     * Para editar una cita.
     *
     * @param oldCustomerDate cita que se quiere editar
     * @param newCustomerDate cita editada
     * @return true si la cita se editó, si no, false
     */
    @Override
    public boolean edit(CustomerDate oldCustomerDate, CustomerDate newCustomerDate) {
        refresh();
        if (customerDateStack.contains(oldCustomerDate)) {
            customerDateStack.pop(oldCustomerDate);
            customerDateStack.push(newCustomerDate);
            datePersistence.write(customerDateStack);
            refresh();
        }
        return customerDateStack.contains(newCustomerDate);
    }

    /**
     * Para remover una cita.
     *
     * @param customerDate que se quiere remover
     * @return true si la cita se removió, si no, false
     */
    @Override
    public boolean remove(CustomerDate customerDate) {
        refresh();
        if (customerDateStack.contains(customerDate)) {
            customerDateStack.pop(customerDate);
            return datePersistence.write(customerDateStack);
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
        for (int i = 0; i < customerDateStack.size()-1; i++) {
            if (customerDateStack.getByAcc(i).getCustomerID() == iD) {
                return customerDateStack.getByAcc(i);
            }
            if (customerDateStack.getByAcc(i).getAdminID() == iD) {
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
        for (int i = 0; i < customerDateStack.size(); i++) {
            if (customerDateStack.getByAcc(i).getCustomerID() == adminID) {
                return null;
            }
            if (customerDateStack.getByAcc(i).getAdminID() == adminID) {
                tempCustomerDateStack.push(customerDateStack.getByAcc(i));
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
        return customerDateStack;
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
        datePersistence = new DatePersistence();
        /*CustomerDateStack tempCustomerDateStack = new CustomerDateStack();
        if (datePersistence.read() != null) {
            customerDateStack = datePersistence.read();
            for (int i = 0; i < customerDateStack.size(); i++) {
                tempCustomerDateStack.push(customerDateStack.get(i));
            }
        }
        customerDateStack = tempCustomerDateStack;*/
        if (datePersistence.read() != null) {
            customerDateStack = datePersistence.read();
        }
    }
}
