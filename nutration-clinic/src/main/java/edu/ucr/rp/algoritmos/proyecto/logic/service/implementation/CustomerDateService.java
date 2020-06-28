package edu.ucr.rp.algoritmos.proyecto.logic.service.implementation;

import edu.ucr.rp.algoritmos.proyecto.logic.domain.AdminAvailability;
import edu.ucr.rp.algoritmos.proyecto.logic.domain.CustomerDate;
import edu.ucr.rp.algoritmos.proyecto.logic.persistance.implementation.CustomerDatePersistence;
import edu.ucr.rp.algoritmos.proyecto.logic.service.interfaces.DateService;

import edu.ucr.rp.algoritmos.proyecto.logic.tdamethods.implementation.CustomerDateStack;
import edu.ucr.rp.algoritmos.proyecto.util.Utility;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase maneja en conjunto con la persistencia, los TDA(stack) y los objetos tipo CustomerDate las citas
 * registradas en del sistema.
 *
 * @author Luis Carlos Aguilar
 */
public class CustomerDateService implements DateService<CustomerDate> {
    public CustomerDateStack stack;
    private CustomerDatePersistence customerDatePersistence;
    private AdminAvailabilityGeneralService adminAvailabilityService;
    private static CustomerDateService instance;
    private Utility utility;

    /**
     * Constructor
     */
    private CustomerDateService() {
        stack = new CustomerDateStack();
        customerDatePersistence = new CustomerDatePersistence();
        adminAvailabilityService = AdminAvailabilityGeneralService.getInstance();
        utility = new Utility();
        refresh();
    }

    /**
     * Singleton Pattern
     */
    public static CustomerDateService getInstance() {
        if (instance == null)
            instance = new CustomerDateService();
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
        if (customerDate != null) {
            deleteAdminAvailability(customerDate); //TODO revisar
            stack.push(customerDate);
            utility.historyApp("Cita agregada para el usuario " + customerDate.getCustomerID());
            return customerDatePersistence.write(stack);
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
        if (!stack.isEmpty()) {
            stack = editCustomerDate(newCustomerDate);
            editAdminAvailability(oldCustomerDate, newCustomerDate);
            utility.historyApp("Cita editada para el usuario " + oldCustomerDate.getCustomerID());
            refresh();
            return customerDatePersistence.write(stack);
        }
        return false;
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
        if (!stack.isEmpty() && customerDate != null) {
            stack = removeCustomerDate(customerDate);
            addAdminAvailability(customerDate);
            utility.historyApp("Cita removida para el usuario " + customerDate.getCustomerID());
            CustomerDatesHistoryService customerDatesHistoryService = CustomerDatesHistoryService.getInstance();
            customerDatesHistoryService.add(customerDate);
            return customerDatePersistence.write(stack);
        }
        return false;
    }

    private CustomerDateStack removeCustomerDate(CustomerDate customerDate) {
        refresh();
        CustomerDateStack auxCustomerDateStack = new CustomerDateStack();
        int size = stack.size();
        for (int i = 0; i < size; i++) {
            CustomerDate auxCustomerDate = stack.pop();
            if (auxCustomerDate.getCustomerID() != customerDate.getCustomerID()) {
                auxCustomerDateStack.push(auxCustomerDate);
            }
        }
        refresh();
        return auxCustomerDateStack;
    }

    private CustomerDateStack editCustomerDate(CustomerDate customerDate) {
        CustomerDateStack auxCustomerDateStack = new CustomerDateStack();
        for (int i = 0; i < stack.size(); i++) {
            CustomerDate auxCustomerDate = get(i);
            if (auxCustomerDate.getCustomerID() != customerDate.getCustomerID()) {
                auxCustomerDateStack.push(auxCustomerDate);
            } else {
                auxCustomerDateStack.push(customerDate);
            }
        }
        refresh();
        return auxCustomerDateStack;
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
        int size = stack.size();
        for (int i = 0; i < size; i++) {
            CustomerDate customerDate = stack.pop();
            if (customerDate.getCustomerID() == iD) {
                return customerDate;
            }
            if (customerDate.getAdminID() == iD) {
                return null;
            }
        }
        refresh();
        return null;
    }

    /**
     * Para obtener una cita a partir de una hora y ID de administrador.
     *
     * @param hour    de una cita
     * @param adminID de un usuario
     * @return true si el hour ingresado corresponde a una cita, si no, false
     */
    public CustomerDate getByHourAndAdminID(String hour, int adminID) {
        refresh();
        int size = stack.size();
        for (int i = 0; i < size; i++) {
            CustomerDate customerDate = stack.pop();
            if (customerDate.getAdminID() == adminID && customerDate.getHour().equals(hour)) {
                return customerDate;
            }
        }
        refresh();
        return null;
    }

    /**
     * Obtiene una lista de las horas de las citas que estén a nombre de un administrador.
     *
     * @param adminID
     * @return
     */
    public List<String> getDatesByAdminID(int adminID) {
        refresh();
        LocalDate localDate = LocalDate.now();
        String date = localDate.getDayOfMonth() + "/" + localDate.getMonthValue() + "/" + localDate.getYear();

        List<String> list = new ArrayList<>();
        int size = stack.size();
        for (int i = 0; i < size; i++) {
            CustomerDate customerDate = stack.pop();
            if (customerDate.getCustomerID() == adminID) {
                return null;
            }
            if (customerDate.getAdminID() == adminID) {
                if (date.equals(customerDate.getDate())) {
                    list.add(customerDate.getHour());
                }
            }
        }
        return list;
    }

    /**
     * Obtiene todas las citas registradas en el sistema.
     *
     * @return pila de citas.
     */
    @Override
    public CustomerDateStack getAll() {
        refresh();
        return stack;
    }

    public List getNamesOfCustomersByDates(CustomerDateStack customerDateStack) {
        List customerNamesByDates = new ArrayList();
        UserService userService = UserService.getInstance();
        for (int i = 0; i < customerDateStack.size(); i++) {
            String name = userService.getByID(get(i).getCustomerID()).getName();
            customerNamesByDates.add(name);
        }
        return customerNamesByDates;
    }

    private CustomerDate get(int index) {
        int count = 0;
        int size = stack.size();
        for (int i = 0; i < size; i++) {
            CustomerDate customerDate = stack.pop();
            if (index == count) {
                refresh();
                return customerDate;
            }
        }
        refresh();
        return null;
    }

    private void addAdminAvailability(CustomerDate customerDate) {
        CustomerDate newCustomerDate = customerDate;

        adminAvailabilityService = AdminAvailabilityGeneralService.getInstance();
        AdminAvailability adminAvailability = adminAvailabilityService.getByID(customerDate.getAdminID());

        AdminAvailability adminAvailability1 = adminAvailability;
        String date = customerDate.getDate();
        String hour = customerDate.getHour();

        List hourList = adminAvailability1.getAdminAvailability().get(date);
        hourList.add(hour);
        adminAvailabilityService.add(adminAvailability);
    }

    private void deleteAdminAvailability(CustomerDate customerDate) {
        adminAvailabilityService = AdminAvailabilityGeneralService.getInstance();
        AdminAvailability adminAvailability = adminAvailabilityService.getByID(customerDate.getAdminID());

        AdminAvailability adminAvailability1 = adminAvailability;
        String date = customerDate.getDate();
        String hour = customerDate.getHour();

        List<String> hourList = adminAvailability1.getAdminAvailability().get(date);
        hourList.remove(hour);
        adminAvailabilityService.edit(adminAvailability, adminAvailability1);
    }

    private void editAdminAvailability(CustomerDate oldCustomerDate, CustomerDate newCustomerDate) {
        if (oldCustomerDate.getAdminID() != newCustomerDate.getAdminID()) { //se cambia de doctor
            AdminAvailability previousAdminAvailability = adminAvailabilityService.getByID(oldCustomerDate.getAdminID());
            AdminAvailability newAdminAvailabilityPrevious = previousAdminAvailability;
            String datePrevious = oldCustomerDate.getDate();
            String hourPrevious = oldCustomerDate.getHour();
            List<String> hourListPrevious = newAdminAvailabilityPrevious.getAdminAvailability().get(datePrevious);
            hourListPrevious.add(hourPrevious);
            adminAvailabilityService.edit(previousAdminAvailability, newAdminAvailabilityPrevious);

            AdminAvailability afterAdminAvailability = adminAvailabilityService.getByID(newCustomerDate.getAdminID());
            AdminAvailability newAdminAvailabilityAfter = afterAdminAvailability;
            String dateAfter = oldCustomerDate.getDate();
            String hourAfter = oldCustomerDate.getHour();
            List<String> hourListAfter = newAdminAvailabilityAfter.getAdminAvailability().get(dateAfter);
            hourListAfter.remove(hourAfter);
            adminAvailabilityService.edit(afterAdminAvailability, newAdminAvailabilityAfter);
        } else if (!oldCustomerDate.getDate().equals(newCustomerDate.getDate()) || !oldCustomerDate.getHour().equals(newCustomerDate.getHour())) { //cambia la fecha y hora
            AdminAvailability adminAvailability = adminAvailabilityService.getByID(oldCustomerDate.getAdminID());
            String datePrevious = oldCustomerDate.getDate();
            String hourPrevious = oldCustomerDate.getHour();
            List hourListPrevious = adminAvailability.getAdminAvailability().get(datePrevious);
            hourListPrevious.add(hourPrevious);
            adminAvailabilityService.edit(adminAvailability, adminAvailability);

            AdminAvailability newAdminAvailability = adminAvailabilityService.getByID(newCustomerDate.getAdminID());
            String dateAfter = newCustomerDate.getDate();
            String hourAfter = newCustomerDate.getHour();
            List hourListAfter = newAdminAvailability.getAdminAvailability().get(dateAfter);
            hourListAfter.remove(hourAfter);
            adminAvailabilityService.edit(newAdminAvailability, newAdminAvailability);
        }
    }


    /**
     * Refresca la lista de citas
     */
    @Override
    public void refresh() {
        customerDatePersistence = new CustomerDatePersistence();
        CustomerDateStack tempAdminAnnotationQueue = new CustomerDateStack();

        stack = customerDatePersistence.read();
        if (stack != null) {
            int size = stack.size();
            for (int i = 0; i < size; i++) {
                tempAdminAnnotationQueue.push(stack.pop());
            }
        }
        stack = tempAdminAnnotationQueue;
    }
}
