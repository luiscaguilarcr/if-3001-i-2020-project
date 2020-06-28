package edu.ucr.rp.algoritmos.proyecto.logic.service.implementation;

import edu.ucr.rp.algoritmos.proyecto.logic.domain.CustomerDate;
import edu.ucr.rp.algoritmos.proyecto.logic.persistance.implementation.CustomerDatesHistoryPersistence;
import edu.ucr.rp.algoritmos.proyecto.logic.service.interfaces.AuxService;
import edu.ucr.rp.algoritmos.proyecto.logic.tdamethods.implementation.CustomerDatesHistoryTree;

/**
 * Esta clase maneja en conjunto con la persistencia y los objetos tipo CustomerDate el historial de las
 * citas de un cliente agregadas en el sistema.
 *
 * @author Luis Carlos Aguilar
 */
public class CustomerDatesHistoryService implements AuxService<CustomerDate, CustomerDatesHistoryTree> {
    public CustomerDatesHistoryTree customerDatesHistoryTree;
    private CustomerDatesHistoryPersistence historyDatesByCustomerPersistence;
    private static CustomerDatesHistoryService instance;

    /**
     * Constructor
     */
    private CustomerDatesHistoryService() {
        customerDatesHistoryTree = new CustomerDatesHistoryTree();
        historyDatesByCustomerPersistence = new CustomerDatesHistoryPersistence();
        refresh();
    }

    /**
     * Singleton Pattern
     */
    public static CustomerDatesHistoryService getInstance() {
        if (instance == null)
            instance = new CustomerDatesHistoryService();
        return instance;
    }

    /**
     * Para agregar al historial una cita ya realizada.
     *
     * @param customerDate que se quiere agregar
     * @return true si la cita fue agregada, si no, false
     */
    @Override
    public boolean add(CustomerDate customerDate) {
        refresh();
        if (validateAddition(customerDate)) {
            customerDatesHistoryTree.insert(customerDate);
            return historyDatesByCustomerPersistence.write(customerDatesHistoryTree);
        }
        return false;
    }

    /**
     * Para remover del historial una cita ya realizada.
     *
     * @param customerDate que se quiere remover
     * @return true si la cita ya realizada se removió, si no, false
     */
    @Override
    public boolean remove(CustomerDate customerDate) {
        refresh();
        if (customerDatesHistoryTree.search(customerDate)) {
            customerDatesHistoryTree.delete(customerDate);
            return historyDatesByCustomerPersistence.write(customerDatesHistoryTree);
        }
        return false;
    }

    /**
     * Para obtener el historial las citas ya realizada a partir de un ID de cliente.
     *
     * @param iD de un usuario
     * @return lista de las citas ya realizada a partir de un ID, si el iD no corresponde a algún cliente, null
     */
    @Override
    public CustomerDatesHistoryTree getByID(int iD) {
        refresh();
        CustomerDatesHistoryTree datesByID = new CustomerDatesHistoryTree();
        for (int i = 0; i < customerDatesHistoryTree.size(); i++) {
            if (customerDatesHistoryTree.get(i) != null) {
                if (customerDatesHistoryTree.get(i).getCustomerID() == iD) {
                    datesByID.insert(customerDatesHistoryTree.get(i));
                }
            }
        }
        return datesByID;
    }

    /**
     * Refresca la lista de citas ya realizadas en el sistema.
     */
    private void refresh() {
        //Lee el archivo
        Object object = historyDatesByCustomerPersistence.read();
        //Valida que existe y lo sustituye por la lista en memoria
        if (object != null) {
            customerDatesHistoryTree = (CustomerDatesHistoryTree) object;
        }
    }

    private boolean validateAddition(CustomerDate customerDate) {
        if (customerDatesHistoryTree.search(customerDate)) return false;
        return true;
    }
}
