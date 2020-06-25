package edu.ucr.rp.algoritmos.proyecto.logic.persistance.implementation;

import edu.ucr.rp.algoritmos.proyecto.logic.domain.CustomerDate;
import edu.ucr.rp.algoritmos.proyecto.logic.persistance.interfaces.Persistence;
import edu.ucr.rp.algoritmos.proyecto.util.JsonUtil;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

public class HistoryDatesByCustomerPersistence implements Persistence<CustomerDate, List> {
    private final String path = "files/historyUserDate.json";
    private final JsonUtil jsonUtil = new JsonUtil();

    /**
     * Para guardar el historial de citas de un usuario.
     * @param list que se quiere guardar
     * @return true si se guardó, si no, false
     */
    @Override
    public boolean write(List list) {
        if (list == null) return false;
        return saveHistory(list);
    }

    private boolean saveHistory(List list) {
        jsonUtil.toFile(new File(path), list);
        return true;
    }

    /**
     * Para leer una lista del historial de citas realizadas por un usuario.
     * @return AVL del historial de la aplicación
     */
    @Override
    public List read() {
        return readHistory();
    }

    private List readHistory(){
        File file = new File(path);
        if(file.exists()){
            try {
                return jsonUtil.asObject(file.toURI().toURL(), List.class);
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
