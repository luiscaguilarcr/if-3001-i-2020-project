package edu.ucr.rp.algoritmos.proyecto.logic.persistance.implementation;

import edu.ucr.rp.algoritmos.proyecto.logic.domain.HistoryApp;
import edu.ucr.rp.algoritmos.proyecto.logic.tdamethods.implementation.HistoryAppAVL;
import edu.ucr.rp.algoritmos.proyecto.logic.persistance.interfaces.Persistence;
import edu.ucr.rp.algoritmos.proyecto.util.files.JsonUtil;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

public class HistoryAppPersistence implements Persistence<HistoryApp, HistoryAppAVL> {
    private final String path = "files/historyApp.json";
    private final JsonUtil jsonUtil = new JsonUtil();

    /**
     * Para guardar el historial de la aplicación.
     * @param historyAppAVL que se quiere guardar
     * @return true si se guardó, si no, false
     */
    @Override
    public boolean write(HistoryAppAVL historyAppAVL) {
        if (historyAppAVL == null) return false;
        return saveHistory(historyAppAVL);
    }

    private boolean saveHistory(HistoryAppAVL historyAppAVL) {
        jsonUtil.toFile(new File(path), historyAppAVL);
        return true;
    }

    /**
     * Para leer un AVL de historial de la aplicación.
     * @return AVL del historial de la aplicación
     */
    @Override
    public HistoryAppAVL read() {
        return readHistory();
    }

    private HistoryAppAVL readHistory(){
        File file = new File(path);
        if(file.exists()){
            try {
                return jsonUtil.asObject(file.toURI().toURL(), HistoryAppAVL.class);
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
