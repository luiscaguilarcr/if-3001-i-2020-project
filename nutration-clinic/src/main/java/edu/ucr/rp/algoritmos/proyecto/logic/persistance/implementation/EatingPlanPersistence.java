package edu.ucr.rp.algoritmos.proyecto.logic.persistance.implementation;

import edu.ucr.rp.algoritmos.proyecto.logic.domain.EatingPlan;
import edu.ucr.rp.algoritmos.proyecto.logic.persistance.interfaces.Persistence;
import edu.ucr.rp.algoritmos.proyecto.util.files.JsonUtil;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

public class EatingPlanPersistence implements Persistence<EatingPlan, List> {
    private final String path = "files/eatingPlan.json";
    private final JsonUtil jsonUtil = new JsonUtil();

    /**
     * Para guardar planes de comidas.
     * @param list que se quiere guardar
     * @return true si se guardó, si no, false
     */
    @Override
    public boolean write(List list) {
        if (list == null) return false;
        return saveEatingPlan(list);

    }

    private boolean saveEatingPlan(List list) {
        jsonUtil.toFile(new File(path), list);
        return true;
    }

    /**
     * Para leer una lista de planes de comidas.
     * @return lista de planes de comidas
     */
    @Override
    public List<EatingPlan> read() {
        return readEatingPlan();
    }

    private List<EatingPlan> readEatingPlan(){
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
     * Elimina todos los planes de comidas del sistema.
     * @return true si se eliminaron todos los planes de comidas, si no, false
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
