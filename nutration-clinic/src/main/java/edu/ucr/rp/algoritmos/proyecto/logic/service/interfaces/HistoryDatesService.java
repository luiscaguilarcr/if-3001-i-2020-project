package edu.ucr.rp.algoritmos.proyecto.logic.service.interfaces;

import edu.ucr.rp.algoritmos.proyecto.logic.domain.CustomerDate;

public interface HistoryDatesService  extends Service<CustomerDate>{
    Object getAllByID(int iD);
}
