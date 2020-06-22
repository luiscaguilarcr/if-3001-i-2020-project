package edu.ucr.rp.algoritmos.proyecto.logic.service.implementation;

import edu.ucr.rp.algoritmos.proyecto.logic.domain.HistoryApp;
import edu.ucr.rp.algoritmos.proyecto.logic.tdamethods.implementation.HistoryAppAVL;
import edu.ucr.rp.algoritmos.proyecto.logic.service.interfaces.Service;

/**
 * Maneja el historial general(hostorial) hecho en la app
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
