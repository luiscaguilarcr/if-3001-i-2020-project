package edu.ucr.rp.algoritmos.proyecto.logic.service.implementation;

import edu.ucr.rp.algoritmos.proyecto.logic.domain.AdminAnnotation;
import edu.ucr.rp.algoritmos.proyecto.logic.persistance.implementation.AnnotationPersistence;
import edu.ucr.rp.algoritmos.proyecto.logic.service.interfaces.AuxService2;
import edu.ucr.rp.algoritmos.proyecto.logic.tdamethods.implementation.AdminAnnotationQueue;

/**
 * Esta clase maneja en conjunto con la persistencia, los TDA (AdminAnnotationQueue) y los objetos tipo AdminAnnotation
 * las anotaciones realizadas por un administrador en una cita.
 *
 * @author Luis Carlos Aguilar
 */
public class AnnotationService implements AuxService2<AdminAnnotation, AdminAnnotationQueue> {
    public AdminAnnotationQueue queue;
    private AnnotationPersistence annotationPersistence;
    private static AnnotationService instance;

    /**
     * Constructor
     */
    private AnnotationService() {
        queue = new AdminAnnotationQueue();
        annotationPersistence = new AnnotationPersistence();
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
        if (validateAddition(adminAnnotation)) {
            queue.enqueue(adminAnnotation);
            return annotationPersistence.write(queue);
        }
        return false;
    }

    /**
     *
     * @param oldAdminAnnotation
     * @param newAdminAnnotation
     * @return
     */
    @Override
    public boolean edit(AdminAnnotation oldAdminAnnotation, AdminAnnotation newAdminAnnotation) {
        refresh();
        if (queue.contains(oldAdminAnnotation)) {
            queue.dequeue(oldAdminAnnotation);
            queue.enqueue(newAdminAnnotation);
            annotationPersistence.write(queue);
            refresh();
        }
        return queue.contains(newAdminAnnotation);
    }

    /**
     * Para remover las anotaciones realizadas por un administrador en una cita.
     *
     * @param adminAnnotation que se quiere remover
     * @return true si las anotaciones realizadas por un administrador en una cita fueron removidas, si no, false
     */
    @Override
    public boolean remove(AdminAnnotation adminAnnotation) {
        refresh();
        if (queue.contains(adminAnnotation)) {
            queue.dequeue(adminAnnotation);
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
     * @param date que se quiere buscar
     * @param customerID que se quiere buscar
     * @return las anotaciones realizadas a un cliente si los datos son válidos, si no, null
     */
    @Override
    public AdminAnnotation getByDateAndCustomerID(String date, int customerID) {
        refresh();
        AdminAnnotation adminAnnotation;
        for (int i = 0; i < queue.size(); i++) {
            adminAnnotation = queue.get(i);
            if(adminAnnotation != null){
                if(adminAnnotation.getCustomerID() == customerID){
                    if(adminAnnotation.getDate().equals(date)){
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
        //Lee el archivo
        Object object = annotationPersistence.read();
        //Valida que existe y lo sustituye por la lista en memoria
        if (object != null) {
            queue = (AdminAnnotationQueue) object;
        }
    }

    /**
     * Valida si se puede agregar las anotaciones a un cliente.
     * @param adminAnnotation que se quiere agregar
     * @return true si se puede agregar, si no, false
     */
    private boolean validateAddition(AdminAnnotation adminAnnotation) {
        if (queue.contains(adminAnnotation)) return false;
        return true;
    }
}
