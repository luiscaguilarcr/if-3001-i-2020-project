package edu.ucr.rp.algoritmos.proyecto.logic.persistance.implementation;

import edu.ucr.rp.algoritmos.proyecto.logic.domain.AdminAnnotation;
import edu.ucr.rp.algoritmos.proyecto.logic.tdamethods.implementation.AdminAnnotationQueue;
import edu.ucr.rp.algoritmos.proyecto.logic.persistance.interfaces.Persistence;
import edu.ucr.rp.algoritmos.proyecto.util.files.JsonUtil;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

public class AnnotationPersistence implements Persistence<AdminAnnotation, AdminAnnotationQueue> {
    private final String path = "files/annotation.json";
    private final JsonUtil jsonUtil = new JsonUtil();

    /**
     * Para guardar las notas realizadas en una cita.
     * @param adminAnnotationQueue que se quiere guardar
     * @return true si se guardó, si no, false
     */
    @Override
    public boolean write(AdminAnnotationQueue adminAnnotationQueue) {
        if (adminAnnotationQueue == null) return false;
        return saveAdminAnnotation(adminAnnotationQueue);
    }

    private boolean saveAdminAnnotation(AdminAnnotationQueue adminAnnotationQueue) {
        jsonUtil.toFile(new File(path), adminAnnotationQueue);
        return true;
    }

    /**
     * Para leer una cola de notas.
     * @return lista de usuarios
     */
    @Override
    public AdminAnnotationQueue read() {
        return readAdminAnnotation();
    }

    private AdminAnnotationQueue readAdminAnnotation(){
        File file = new File(path);
        if(file.exists()){
            try {
                return jsonUtil.asObject(file.toURI().toURL(), AdminAnnotationQueue.class);
            } catch (MalformedURLException e) {
                System.out.println(e.getMessage());
            }
        }
        return null;
    }

    /**
     * Elimina todos las anotaciones del sistema.
     * @return true si se eliminaron todos los usuarios, si no, false
     */
    @Override
    public boolean deleteAll() {
        try {
            FileUtils.forceDelete(new File(path));
            return true;
        }catch (IOException e){
            return false;
        }
    }
}
