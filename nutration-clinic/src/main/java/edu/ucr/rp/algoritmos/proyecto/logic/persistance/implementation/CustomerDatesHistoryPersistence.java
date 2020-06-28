package edu.ucr.rp.algoritmos.proyecto.logic.persistance.implementation;

import edu.ucr.rp.algoritmos.proyecto.logic.domain.CustomerDate;
import edu.ucr.rp.algoritmos.proyecto.logic.persistance.interfaces.Persistence;
import edu.ucr.rp.algoritmos.proyecto.logic.tdamethods.implementation.CustomerDatesHistoryTree;
import edu.ucr.rp.algoritmos.proyecto.util.files.JsonUtil;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

public class CustomerDatesHistoryPersistence implements Persistence<CustomerDate, CustomerDatesHistoryTree> {
    private final String path = "files/customerDatesHistory.json";
    private final JsonUtil jsonUtil = new JsonUtil();

    /**
     * Para guardar el historial de citas de un usuario.
     * @param tree que se quiere guardar
     * @return true si se guardó, si no, false
     */
    @Override
    public boolean write(CustomerDatesHistoryTree tree) {
        if (tree == null) return false;
        return saveHistory(tree);
    }

    private boolean saveHistory(CustomerDatesHistoryTree tree) {
        jsonUtil.toFile(new File(path), tree);
        return true;
    }

    /**
     * Para leer una lista del historial de citas realizadas por un usuario.
     * @return AVL del historial de la aplicación
     */
    @Override
    public CustomerDatesHistoryTree read() {
        return readHistory();
    }

    private CustomerDatesHistoryTree readHistory(){
        File file = new File(path);
        if(file.exists()){
            try {
                return jsonUtil.asObject(file.toURI().toURL(), CustomerDatesHistoryTree.class);
            } catch (MalformedURLException e) {
                System.out.println(e.getMessage());
            }
        }
        return null;
    }

    /**
     * Elimina el historial de la aplicación en el sistema.
     * @return true si se eliminó el historial de la aplicación, si no, false
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
