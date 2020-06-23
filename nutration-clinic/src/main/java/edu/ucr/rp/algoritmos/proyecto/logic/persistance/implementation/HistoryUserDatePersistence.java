package edu.ucr.rp.algoritmos.proyecto.logic.persistance.implementation;

import edu.ucr.rp.algoritmos.proyecto.logic.domain.CustomerDate;
import edu.ucr.rp.algoritmos.proyecto.logic.persistance.interfaces.Persistence;
import edu.ucr.rp.algoritmos.proyecto.logic.tdamethods.implementation.CustomerDateStack;
import edu.ucr.rp.algoritmos.proyecto.logic.tdamethods.implementation.HistoryAppAVL;
import edu.ucr.rp.algoritmos.proyecto.util.JsonUtil;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

public class HistoryUserDatePersistence implements Persistence<CustomerDate, CustomerDateStack> {
    private final String path = "files/historyUserDate.json";
    private final JsonUtil jsonUtil = new JsonUtil();

    /**
     * Para guardar el historial de la aplicación.
     * @param customerDateStack que se quiere guardar
     * @return true si se guardó, si no, false
     */
    @Override
    public boolean write(CustomerDateStack customerDateStack) {
        if (customerDateStack == null) return false;
        return saveHistory(customerDateStack);
    }

    private boolean saveHistory(CustomerDateStack customerDateStack) {
        jsonUtil.toFile(new File(path), customerDateStack);
        return true;
    }

    /**
     * Para leer un AVL de historial de la aplicación.
     * @return AVL del historial de la aplicación
     */
    @Override
    public CustomerDateStack read() {
        return readHistory();
    }

    private CustomerDateStack readHistory(){
        File file = new File(path);
        if(file.exists()){
            try {
                return jsonUtil.asObject(file.toURI().toURL(), CustomerDateStack.class);
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
