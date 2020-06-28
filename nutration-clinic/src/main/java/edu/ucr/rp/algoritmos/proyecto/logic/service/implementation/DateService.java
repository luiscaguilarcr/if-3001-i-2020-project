/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucr.rp.algoritmos.proyecto.logic.service.implementation;

import edu.ucr.rp.algoritmos.proyecto.logic.domain.AdminAvailability;
import edu.ucr.rp.algoritmos.proyecto.logic.domain.CustomerDate;
import edu.ucr.rp.algoritmos.proyecto.logic.domain.User;
import edu.ucr.rp.algoritmos.proyecto.logic.persistance.implementation.DatePersistence;
import edu.ucr.rp.algoritmos.proyecto.logic.service.interfaces.Service;
import edu.ucr.rp.algoritmos.proyecto.logic.tdamethods.implementation.CustomerDateStack;
import edu.ucr.rp.algoritmos.proyecto.util.Utility;
import edu.ucr.rp.algoritmos.proyecto.util.test.TestUtility;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Esta clase maneja en conjunto con la persistencia, los TDA(stack) y los objetos tipo CustomerDate las citas
 * registradas en del sistema.
 *
 * @author Luis Carlos Aguilar
 */
public class DateService implements Service<CustomerDate, CustomerDateStack> {
    public CustomerDateStack stack;
    private DatePersistence datePersistence;
    private AdminAvailabilityService adminAvailabilityService;
    private static DateService instance;
    private Utility utility;

    /**
     * Constructor
     */
    private DateService() {
        stack = new CustomerDateStack();
        datePersistence = new DatePersistence();
        adminAvailabilityService = AdminAvailabilityService.getInstance();
        utility = new Utility();
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
            deleteAdminAvailability(customerDate); //TODO revisar
            stack.push(customerDate);
            //utility.historyApp("Cita agregada para el usuario " + customerDate.getCustomerID());
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
            editAdminAvailability(oldCustomerDate, newCustomerDate);
            //utility.historyApp("Cita editada para el usuario " + oldCustomerDate.getCustomerID());
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
            addAdminAvailability(customerDate);
            //utility.historyApp("Cita removida para el usuario " + customerDate.getCustomerID());
            CustomerReportService customerReportService = CustomerReportService.getInstance();
            customerReportService.add(customerDate);
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
        for (int i = 0; i < stack.size(); i++) {
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
     * Para obtener una cita a partir de una hora y ID de administrador.
     *
     * @param hour de una cita
     * @param adminID de un usuario
     * @return true si el hour ingresado corresponde a una cita, si no, false
     */
    public CustomerDate getByHourAndAdminID(String hour, int adminID) {
        refresh();
        for (int i = 0; i < stack.size(); i++) {
            CustomerDate customerDate = stack.getByAcc(i);
            if (customerDate.getAdminID() == adminID && customerDate.getHour().equals(hour)) {
                return customerDate;
            }
        }
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
        for (int i = 0; i < stack.size(); i++) {
            CustomerDate customerDate = stack.getByAcc(i);

            if (customerDate.getCustomerID() == adminID) {
                return null;
            }
            if (customerDate.getAdminID() == adminID) {
                if (date.equals(customerDate.getDate())) {
                    list.add(customerDate.getCustomerID() +" : "+customerDate.getHour());
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
            String name = userService.getByID(customerDateStack.getByAcc(i).getCustomerID()).getName();
            customerNamesByDates.add(name);
        }
        return customerNamesByDates;
    }

    private void addAdminAvailability(CustomerDate customerDate) {
        CustomerDate newCustomerDate = customerDate;

        adminAvailabilityService = AdminAvailabilityService.getInstance();
        AdminAvailability adminAvailability = adminAvailabilityService.getByID(customerDate.getAdminID());

        AdminAvailability adminAvailability1 = adminAvailability;
        String date = customerDate.getDate();
        String hour = customerDate.getHour();

        List hourList = adminAvailability1.getAdminAvailability().get(date);
        hourList.add(hour);
        adminAvailabilityService.add(adminAvailability);
    }

    private void deleteAdminAvailability(CustomerDate customerDate) {
        adminAvailabilityService = AdminAvailabilityService.getInstance();
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
    private void refresh() {
        //Lee el archivo
        Object object = datePersistence.read();
        //Valida que existe y lo sustituye por la lista en memoria
        if (object != null) {
            stack = (CustomerDateStack) object;
        }
    }
}
