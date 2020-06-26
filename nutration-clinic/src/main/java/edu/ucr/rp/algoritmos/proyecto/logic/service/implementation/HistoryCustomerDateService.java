package edu.ucr.rp.algoritmos.proyecto.logic.service.implementation;

import edu.ucr.rp.algoritmos.proyecto.logic.domain.CustomerDate;
import edu.ucr.rp.algoritmos.proyecto.logic.persistance.implementation.HistoryDatesByCustomerPersistence;
import edu.ucr.rp.algoritmos.proyecto.logic.service.interfaces.AuxService;

import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase maneja en conjunto con la persistencia y los objetos tipo CustomerDate el historial de las
 * citas de un cliente agregadas en el sistema.
 *
 * @author Luis Carlos Aguilar
 */
public class HistoryCustomerDateService implements AuxService<CustomerDate> {
    public List<CustomerDate> list;
    private HistoryDatesByCustomerPersistence historyDatesByCustomerPersistence;
    private static HistoryCustomerDateService instance;

    /**
     * Constructor
     */
    private HistoryCustomerDateService() {
        list = new ArrayList();
        historyDatesByCustomerPersistence = new HistoryDatesByCustomerPersistence();
        refresh();
    }

    /**
     * Singleton Pattern
     */
    public static HistoryCustomerDateService getInstance() {
        if (instance == null)
            instance = new HistoryCustomerDateService();
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
            list.add(customerDate);
            return historyDatesByCustomerPersistence.write(list);
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
        if (list.contains(customerDate)) {
            list.remove(customerDate);
            return historyDatesByCustomerPersistence.write(list);
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
    public List getByID(int iD) {
        refresh();
        List datesByID = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getCustomerID() == iD) {
                datesByID.add(list.get(i));
            }
        }
        if (datesByID.isEmpty()) {
            return null;
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
            list = (List<CustomerDate>) object;
        }
    }

    private boolean validateAddition(CustomerDate customerDate) {
        if (list.contains(customerDate)) return false;
        return true;
    }
}
