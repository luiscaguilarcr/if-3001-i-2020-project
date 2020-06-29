package edu.ucr.rp.algoritmos.proyecto.logic.service.implementation;

import edu.ucr.rp.algoritmos.proyecto.logic.domain.AdminAnnotation;
import edu.ucr.rp.algoritmos.proyecto.logic.persistance.implementation.AnnotationPersistence;
import edu.ucr.rp.algoritmos.proyecto.logic.service.interfaces.AnnotationService;
import edu.ucr.rp.algoritmos.proyecto.logic.tdamethods.implementation.AdminAnnotationQueue;
import edu.ucr.rp.algoritmos.proyecto.util.Utility;

/**
 * Esta clase maneja en conjunto con la persistencia, los TDA (AdminAnnotationQueue) y los objetos tipo AdminAnnotation
 * las anotaciones realizadas por un administrador en una cita.
 *
 * @author Luis Carlos Aguilar
 */
public class AdminAnnotationService implements AnnotationService<AdminAnnotation> {
    public AdminAnnotationQueue queue;
    private AnnotationPersistence annotationPersistence;
    private static AdminAnnotationService instance;
    private Utility utility;
    private CustomerDateService customerDateService;
    private CustomerDatesHistoryService customerDatesHistoryService;

    /**
     * Constructor
     */
    private AdminAnnotationService() {
        queue = new AdminAnnotationQueue();
        annotationPersistence = new AnnotationPersistence();
        utility = new Utility();
//        customerDatesHistoryService = CustomerDatesHistoryService.getInstance();
        customerDateService = CustomerDateService.getInstance();
        refresh();
    }

    /**
     * Singleton Pattern
     */
    public static AdminAnnotationService getInstance() {
        if (instance == null)
            instance = new AdminAnnotationService();
        return instance;
    }

    /**
     * Para agregar las anotaciones realizadas por un administrador en una cita.
     *
     * @param adminAnnotation que se quiere agregar
     * @return true si se agregaron las anotaciones realizadas por un administrador en una cita, si no, false
     */
    @Override
    public boolean add(AdminAnnotation adminAnnotation) {
        refresh();
        if (adminAnnotation != null) {
            customerDatesHistoryService.add(customerDateService.getByID(adminAnnotation.getCustomerID()));
            queue.enQueue(adminAnnotation);
            customerDateService.remove(customerDateService.getByID(adminAnnotation.getCustomerID()));
            utility.historyApp("Anotaciones agregadas para el usuario " + adminAnnotation.getCustomerID() + " en la fecha " + adminAnnotation.getDate());
            return annotationPersistence.write(queue);
        }
        return false;
    }

    /**
     * Para obtener una cola de las anotaciones realizadas por un administrador en una o varias citas a partir de un ID.
     *
     * @param iD de un usuario
     * @return cola de las anotaciones realizadas por un administrador a un usuario si el iD ingresado corresponde a un usuario, si no, null
     */
    @Override
    public AdminAnnotationQueue getAllByID(int iD) {
        refresh();
        AdminAnnotationQueue annotationsByID = new AdminAnnotationQueue();
        int size = queue.size();
        for (int i = 0; i < size; i++) {
            AdminAnnotation adminAnnotation = queue.deQueue();
            if (adminAnnotation != null) {
                if (adminAnnotation.getCustomerID() == iD) {
                    annotationsByID.enQueue(adminAnnotation);
                }
            }
        }
        refresh();
        return annotationsByID;
    }

    /**
     * Para obtener las anotaciones realizadas en una fecha específica a un usuario en específico.
     *
     * @param date       que se quiere buscar
     * @param customerID que se quiere buscar
     * @return las anotaciones realizadas a un cliente si los datos son válidos, si no, null
     */
    @Override
    public AdminAnnotation getByDateAndCustomerID(String date, int customerID) {
        refresh();
        int size = queue.size();
        for (int i = 0; i < size; i++) {
            AdminAnnotation adminAnnotation = queue.deQueue();
            if (adminAnnotation != null) {
                if (adminAnnotation.getCustomerID() == customerID) {
                    if (adminAnnotation.getDate().equals(date)) {
                        refresh();
                        return adminAnnotation;
                    }
                }
            }
        }
        refresh();
        return null;
    }

    /**
     * Refresca la cola de anotaciones realizadas por los administradores.
     */
    @Override
    public void refresh() {
        annotationPersistence = new AnnotationPersistence();
        AdminAnnotationQueue tempAdminAnnotationQueue = new AdminAnnotationQueue();

        queue = annotationPersistence.read();
        if (queue != null) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                tempAdminAnnotationQueue.enQueue(queue.deQueue());
            }
        }
        queue = tempAdminAnnotationQueue;
    }
}
