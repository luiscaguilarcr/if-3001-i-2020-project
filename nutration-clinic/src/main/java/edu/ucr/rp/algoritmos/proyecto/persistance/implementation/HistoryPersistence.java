package edu.ucr.rp.algoritmos.proyecto.persistance.implementation;

import edu.ucr.rp.algoritmos.proyecto.domain.HistoryApp;
import edu.ucr.rp.algoritmos.proyecto.logic.tdamethods.implementation.HistoryAppAVL;
import edu.ucr.rp.algoritmos.proyecto.persistance.interfaces.Persistence;

/**
 * Permite persistir la información que el usuario realice
 */
public class HistoryPersistence implements Persistence<HistoryApp, HistoryAppAVL> {

    @Override
    public boolean write(HistoryAppAVL historyAppAVL) {
        return false;
    }

    @Override
    public HistoryAppAVL read() {
        return null;
    }

    @Override
    public boolean deleteAll() {
        return false;
    }
}
