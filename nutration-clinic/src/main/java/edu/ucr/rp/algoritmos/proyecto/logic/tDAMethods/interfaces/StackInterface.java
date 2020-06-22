package edu.ucr.rp.algoritmos.proyecto.logic.tdamethods.interfaces;

import edu.ucr.rp.algoritmos.proyecto.logic.domain.CustomerDate;

public interface StackInterface {

    public void push(CustomerDate element);

    public void pop(CustomerDate element);

    public CustomerDate peek(CustomerDate element);

    public void modify(CustomerDate element, CustomerDate NewElement);

    public int size();

    public boolean validateFill();

    public boolean validateEmpty();

}
