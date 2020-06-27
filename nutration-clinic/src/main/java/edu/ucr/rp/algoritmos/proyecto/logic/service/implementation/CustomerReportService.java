package edu.ucr.rp.algoritmos.proyecto.logic.service.implementation;

import edu.ucr.rp.algoritmos.proyecto.logic.domain.CustomerDate;
import edu.ucr.rp.algoritmos.proyecto.logic.persistance.implementation.CustomerReportPersistence;
import edu.ucr.rp.algoritmos.proyecto.logic.service.interfaces.AuxService;
import edu.ucr.rp.algoritmos.proyecto.logic.tdamethods.implementation.CustomerReportTree;

/**
 * Esta clase maneja en conjunto con la persistencia y los objetos tipo CustomerDate el historial de las
 * citas de un cliente agregadas en el sistema.
 *
 * @author Luis Carlos Aguilar
 */
public class CustomerReportService implements AuxService<CustomerDate, CustomerReportTree> {
    public CustomerReportTree customerReportTree;
    private CustomerReportPersistence historyDatesByCustomerPersistence;
    private static CustomerReportService instance;

    /**
     * Constructor
     */
    private CustomerReportService() {
        customerReportTree = new CustomerReportTree();
        historyDatesByCustomerPersistence = new CustomerReportPersistence();
        refresh();
    }

    /**
     * Singleton Pattern
     */
    public static CustomerReportService getInstance() {
        if (instance == null)
            instance = new CustomerReportService();
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
            customerReportTree.insert(customerDate);
            return historyDatesByCustomerPersistence.write(customerReportTree);
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
        if (customerReportTree.search(customerDate)) {
            customerReportTree.delete(customerDate);
            return historyDatesByCustomerPersistence.write(customerReportTree);
        }
        return false;
    }

    /**
     * Para obtener el historial una cita ya realizada a partir de un ID.
     *
     * @param iD de un usuario
     * @return lista de las citas ya realizada a partir de un ID, si el iD no corresponde a algún usuario, null
     */
    @Override
    public CustomerReportTree getByID(int iD) {
        refresh();
        CustomerReportTree datesByID = new CustomerReportTree();
        for (int i = 0; i < customerReportTree.size(); i++) {
            if (customerReportTree.get(i) != null) {
                if (customerReportTree.get(i).getCustomerID() == iD) {
                    datesByID.insert(customerReportTree.get(i));
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
            customerReportTree = (CustomerReportTree) object;
        }
    }

    private boolean validateAddition(CustomerDate customerDate) {
        if (customerReportTree.search(customerDate)) return false;
        return true;
    }
}
