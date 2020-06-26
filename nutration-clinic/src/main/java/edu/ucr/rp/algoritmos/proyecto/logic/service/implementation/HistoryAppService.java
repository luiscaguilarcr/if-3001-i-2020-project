package edu.ucr.rp.algoritmos.proyecto.logic.service.implementation;

import edu.ucr.rp.algoritmos.proyecto.logic.domain.HistoryApp;
import edu.ucr.rp.algoritmos.proyecto.logic.tdamethods.implementation.HistoryAppAVL;
import edu.ucr.rp.algoritmos.proyecto.logic.service.interfaces.Service;

/**
 * Esta clase maneja en conjunto con la persistencia, los TDA(AVL) y los objetos tipo HistoryApp
 * el historial del sistema.
 *
 * @author Luis Carlos Aguilar
 */
public class HistoryAppService implements Service<HistoryApp, HistoryAppAVL> {
    @Override
    public boolean add(HistoryApp element) {
        return false;
    }

    @Override
    public boolean edit(HistoryApp oldElement, HistoryApp newElement) {
        return false;
    }

    @Override
    public boolean remove(HistoryApp element) {
        return false;
    }

    @Override
    public HistoryApp getByID(int iD) {
        return null;
    }

    @Override
    public HistoryAppAVL getAll() {
        return null;
    }
}
