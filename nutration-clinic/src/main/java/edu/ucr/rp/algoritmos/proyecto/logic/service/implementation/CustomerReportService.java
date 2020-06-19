package edu.ucr.rp.algoritmos.proyecto.logic.service.implementation;

import edu.ucr.rp.algoritmos.proyecto.domain.CustomerReport;
import edu.ucr.rp.algoritmos.proyecto.logic.tdamethods.implementation.CustomerReportTree;
import edu.ucr.rp.algoritmos.proyecto.logic.service.interfaces.Service;

public class CustomerReportService implements Service<CustomerReport, CustomerReportTree> {
    @Override
    public boolean add(CustomerReport element) {
        return false;
    }

    @Override
    public boolean edit(CustomerReport oldElement, CustomerReport newElement) {
        return false;
    }

    @Override
    public boolean remove(CustomerReport element) {
        return false;
    }

    @Override
    public CustomerReport getById(int iD) {
        return null;
    }

    @Override
    public CustomerReportTree getAll() {
        return null;
    }
}
