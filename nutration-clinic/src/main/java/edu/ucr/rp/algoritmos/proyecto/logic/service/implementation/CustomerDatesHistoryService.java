package edu.ucr.rp.algoritmos.proyecto.logic.service.implementation;

import edu.ucr.rp.algoritmos.proyecto.logic.domain.CustomerDate;
import edu.ucr.rp.algoritmos.proyecto.logic.persistance.implementation.CustomerDatesHistoryPersistence;
import edu.ucr.rp.algoritmos.proyecto.logic.service.interfaces.HistoryDatesService;
import edu.ucr.rp.algoritmos.proyecto.logic.tdamethods.implementation.CustomerDatesHistoryTree;

/**
 * Esta clase maneja en conjunto con la persistencia y los objetos tipo CustomerDate el historial de las
 * citas de un cliente agregadas en el sistema.
 *
 * @author Luis Carlos Aguilar
 */
public class CustomerDatesHistoryService implements HistoryDatesService {
    public CustomerDatesHistoryTree tree;
    private CustomerDatesHistoryPersistence customerDatesHistoryPersistence;
    private static CustomerDatesHistoryService instance;
    private CustomerDateService customerDateService;

    /**
     * Constructor
     */
    private CustomerDatesHistoryService() {
        tree = new CustomerDatesHistoryTree();
        customerDatesHistoryPersistence = new CustomerDatesHistoryPersistence();
        customerDateService = CustomerDateService.getInstance();
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
        if (customerDate != null) {
            tree.add(customerDate);
            return customerDatesHistoryPersistence.write(tree);
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
        if (customerDate != null) {
            tree.remove(customerDate);
            return customerDatesHistoryPersistence.write(tree);
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
    public CustomerDatesHistoryTree getAllByID(int iD) {
        refresh();
        CustomerDatesHistoryTree datesByID = new CustomerDatesHistoryTree();
        for (int i = 0; i < tree.size(); i++) {
            CustomerDate customerDate = tree.get(i);
            if (tree.get(i) != null) {
                if (tree.get(i).getCustomerID() == iD) {
                    datesByID.add(tree.get(i));
                }
            }
        }
        return datesByID;
    }

    /**
     * Refresca la lista de citas ya realizadas en el sistema.
     */
    @Override
    public void refresh() {
        customerDatesHistoryPersistence = new CustomerDatesHistoryPersistence();
        CustomerDatesHistoryTree tempCustomerHistoryDates = new CustomerDatesHistoryTree();

        tree = customerDatesHistoryPersistence.read();
        if (tree != null) {
            int size = tree.size()-1;
            for (int i = 0; i < size; i++) {
                tempCustomerHistoryDates.add(tree.get(i));
            }
        }
        tree = tempCustomerHistoryDates;
    }
}
