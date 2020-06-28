package edu.ucr.rp.algoritmos.proyecto.logic.persistance.implementation;

import edu.ucr.rp.algoritmos.proyecto.logic.domain.CustomerDate;
import edu.ucr.rp.algoritmos.proyecto.logic.tdamethods.implementation.CustomerDateStack;
import edu.ucr.rp.algoritmos.proyecto.logic.persistance.interfaces.Persistence;
import edu.ucr.rp.algoritmos.proyecto.util.files.JsonUtil;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

public class CustomerDatePersistence implements Persistence<CustomerDate, CustomerDateStack> {
    private final String path = "files/date.json";
    private final JsonUtil jsonUtil = new JsonUtil();

    /**
     * Para guardar una pila de citas.
     * @param customerDateStack que se quiere guardar
     * @return true si se guardó, si no, false
     */
    @Override
    public boolean write(CustomerDateStack customerDateStack) {
        if (customerDateStack == null) return false;
        return saveDates(customerDateStack);
    }

    private boolean saveDates(CustomerDateStack customerDateStack) {
        jsonUtil.toFile(new File(path), customerDateStack);
        return true;
    }

    /**
     * Para leer una pila de citas.
     * @return pila de citas
     */
    @Override
    public CustomerDateStack read() {
        return readDates();
    }

    private CustomerDateStack readDates(){
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
     * Elimina todas las citas del sistema.
     * @return true si se eliminaron todas las citas, si no, false
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
