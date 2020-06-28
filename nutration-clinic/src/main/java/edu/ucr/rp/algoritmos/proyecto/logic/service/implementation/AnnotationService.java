package edu.ucr.rp.algoritmos.proyecto.logic.service.implementation;

import edu.ucr.rp.algoritmos.proyecto.logic.domain.AdminAnnotation;
import edu.ucr.rp.algoritmos.proyecto.logic.persistance.implementation.AnnotationPersistence;
import edu.ucr.rp.algoritmos.proyecto.logic.persistance.implementation.UserPersistence;
import edu.ucr.rp.algoritmos.proyecto.logic.service.interfaces.AuxService2;
import edu.ucr.rp.algoritmos.proyecto.logic.service.interfaces.AuxService5;
import edu.ucr.rp.algoritmos.proyecto.logic.tdamethods.implementation.AdminAnnotationQueue;
import edu.ucr.rp.algoritmos.proyecto.logic.tdamethods.implementation.UserLinkedList;
import edu.ucr.rp.algoritmos.proyecto.util.Utility;

/**
 * Esta clase maneja en conjunto con la persistencia, los TDA (AdminAnnotationQueue) y los objetos tipo AdminAnnotation
 * las anotaciones realizadas por un administrador en una cita.
 *
 * @author Luis Carlos Aguilar
 */
public class AnnotationService implements AuxService5<AdminAnnotation, AdminAnnotationQueue> {
    public AdminAnnotationQueue queue;
    private AnnotationPersistence annotationPersistence;
    private static AnnotationService instance;
    private Utility utility;
    private CustomerDateService customerDateService;
    private CustomerDatesHistoryService customerDatesHistoryService;

    /**
     * Constructor
     */
    private AnnotationService() {
        queue = new AdminAnnotationQueue();
        annotationPersistence = new AnnotationPersistence();
        utility = new Utility();
        customerDatesHistoryService = CustomerDatesHistoryService.getInstance();
        customerDateService = CustomerDateService.getInstance();
        refresh();
    }

    /**
     * Singleton Pattern
     */
    public static AnnotationService getInstance() {
        if (instance == null)
            instance = new AnnotationService();
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
            queue.enqueue(adminAnnotation);
            customerDatesHistoryService.add(customerDateService.getByID(adminAnnotation.getCustomerID()));
            customerDateService.remove(customerDateService.getByID(adminAnnotation.getCustomerID()));
            //utility.historyApp("Anotaciones agregadas para el usuario " + adminAnnotation.getCustomerID() + " en la fecha " + adminAnnotation.getDate());
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
        for (int i = 0; i < queue.size(); i++) {
            if (queue.get(i) != null) {
                if (queue.get(i).getCustomerID() == iD) {
                    annotationsByID.enqueue(queue.get(i));
                }
            }
        }
        if (annotationsByID.validateEmpty()) {
            return null;
        }
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
        AdminAnnotation adminAnnotation;
        for (int i = 0; i < queue.size(); i++) {
            adminAnnotation = queue.get(i);
            if (adminAnnotation != null) {
                if (adminAnnotation.getCustomerID() == customerID) {
                    if (adminAnnotation.getDate().equals(date)) {
                        return adminAnnotation;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Refresca la cola de anotaciones realizadas por los administradores.
     */
    private void refresh() {
        annotationPersistence = new AnnotationPersistence();
        AdminAnnotationQueue tempAdminAnnotationQueue = new AdminAnnotationQueue();

        queue = annotationPersistence.read();
        if (queue != null) {
            for (int i = 0; i < queue.size(); i++) {
                tempAdminAnnotationQueue.enqueue(queue.dequeue());
            }
        }
        queue = tempAdminAnnotationQueue;
    }
}
