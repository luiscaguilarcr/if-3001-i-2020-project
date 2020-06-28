package edu.ucr.rp.algoritmos.proyecto.logic.service.implementation;

import edu.ucr.rp.algoritmos.proyecto.logic.domain.HistoryApp;
import edu.ucr.rp.algoritmos.proyecto.logic.persistance.implementation.HistoryAppPersistence;
import edu.ucr.rp.algoritmos.proyecto.logic.service.interfaces.HistoryService;
import edu.ucr.rp.algoritmos.proyecto.logic.tdamethods.implementation.HistoryAppAVL;
import edu.ucr.rp.algoritmos.proyecto.util.Utility;

/**
 * Esta clase maneja en conjunto con la persistencia, los TDA(AVL) y los objetos tipo HistoryApp
 * el historial del sistema.
 *
 * @author Luis Carlos Aguilar
 */
public class HistoryAppService implements HistoryService<HistoryApp> {
    public HistoryAppAVL avl;
    private HistoryAppPersistence datePersistence;
    private static HistoryAppService instance;
    private Utility utility;

    /**
     * Constructor
     */
    private HistoryAppService() {
        avl = new HistoryAppAVL();
        datePersistence = new HistoryAppPersistence();
        utility = new Utility();
        refresh();
    }

    /**
     * Singleton Pattern
     */
    public static HistoryAppService getInstance() {
        if (instance == null)
            instance = new HistoryAppService();
        return instance;
    }

    /**
     * Para agregar una cita.
     *
     * @param historyApp que se quiere agregar
     * @return true si la cita fue agregada, si no, false
     */
    @Override
    public boolean add(HistoryApp historyApp) {
        refresh();
        if (historyApp != null) {
            avl.add(historyApp);
            return datePersistence.write(avl);
        }
        return false;
    }

    /**
     * Para remover una cita.
     *
     * @param historyApp que se quiere remover
     * @return true si la cita fue removida, si no, false
     */
    @Override
    public boolean remove(HistoryApp historyApp) {
        refresh();
        if (historyApp != null) {
            avl.remove(historyApp);
            return datePersistence.write(avl);
        }
        return false;
    }

    /**
     * Obtiene todas las citas registradas en el sistema.
     *
     * @return pila de citas.
     */
    @Override
    public HistoryAppAVL getAll() {
        refresh();
        return avl;
    }

    /**
     * Refresca la lista de citas
     */
    @Override
    public void refresh() {
        //Lee el archivo
        Object object = datePersistence.read();
        //Valida que existe y lo sustituye por la lista en memoria
        if (object != null) {
            avl = (HistoryAppAVL) object;
        }
    }
}
