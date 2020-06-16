package edu.ucr.rp.algoritmos.proyecto.logic.lists.interfaces;

public interface StackInterface {
    
   public void push(int x);

    public Object pop();

    public Object peek();

    public int size();

    public boolean isFull();

    public boolean isEmpty();

}
