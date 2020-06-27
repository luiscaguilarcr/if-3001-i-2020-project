package edu.ucr.rp.algoritmos.proyecto.logic.service.implementation;

import edu.ucr.rp.algoritmos.proyecto.logic.domain.EatingPlan;
import edu.ucr.rp.algoritmos.proyecto.logic.persistance.implementation.EatingPlanPersistence;
import edu.ucr.rp.algoritmos.proyecto.logic.service.interfaces.AuxService3;

import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase maneja en conjunto con la persistencia y los objetos tipo EatingPlan los planes de comida
 * que se le ofrecen a los usuarios del sistema.
 *
 * @author Luis Carlos Aguilar
 */
public class EatingPlanService implements AuxService3<EatingPlan, List> {
    public List<EatingPlan> list;
    private EatingPlanPersistence eatingPlanPersistence;
    private static EatingPlanService instance;

    /**
     * Constructor
     */
    private EatingPlanService() {
        list = new ArrayList<>();
        eatingPlanPersistence = new EatingPlanPersistence();
        refresh();
    }

    /**
     * Singleton Pattern
     */
    public static EatingPlanService getInstance() {
        if (instance == null)
            instance = new EatingPlanService();
        return instance;
    }

    /**
     * Para agregar un plan de comida.
     *
     * @param eatingPlan que se quiere agregar
     * @return true si el plan de comida fue agregado, si no, false
     */
    @Override
    public boolean add(EatingPlan eatingPlan) {
        refresh();
        if (validateAddition(eatingPlan)) {
            list.add(eatingPlan);
            return eatingPlanPersistence.write(list);
        }
        return false;
    }

    /**
     * Para remover un plan de comida.
     *
     * @param eatingPlan que se quiere remover
     * @return true si el plan de comida fue removido, si no, false
     */
    @Override
    public boolean remove(EatingPlan eatingPlan) {
        refresh();
        if (list.contains(eatingPlan)) {
            list.remove(eatingPlan);
            return eatingPlanPersistence.write(list);
        }
        return false;
    }

    /**
     * Para obtener una lista de planes de comidas.
     *
     * @return lista de planes de comidas
     */
    @Override
    public List<EatingPlan> getAll() {
        refresh();
        return list;
    }

    /**
     * Para obtener un plane de comida a partir de una cantidad de grasa.
     *
     * @return plan de comida
     */
    public EatingPlan getByID(int planID) {
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getID() == planID){
                return list.get(i);
            }
        }
        return null;
    }

    /**
     * Refresca la lista de planes de comidas.
     */
    private void refresh() {
        //Lee el archivo
        Object object = eatingPlanPersistence.read();
        //Valida que existe y lo sustituye por la lista en memoria
        if (object != null) {
            list = (List<EatingPlan>) object;
        }
    }

    private boolean validateAddition(EatingPlan customerDate) {
        if (list.contains(customerDate)) return false;
        return true;
    }
}
