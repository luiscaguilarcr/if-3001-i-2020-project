package edu.ucr.rp.algoritmos.proyecto.logic.tdamethods.nodes;

import edu.ucr.rp.algoritmos.proyecto.logic.domain.CustomerDate;

/**
 * @author Luis Carlos Aguilar
 */
public class StackNode {
    public CustomerDate data;
    public StackNode next;

    //Constructor
    public StackNode(CustomerDate data) {
        this.data = data;
        this.next = null;
    }

    public StackNode() {
        this.next = null;
    }

    public CustomerDate getData() {
        return data;
    }

    public StackNode setData(CustomerDate data) {
        this.data = data;
        return this;
    }

    public StackNode getNext() {
        return next;
    }

    public void setNext(StackNode next) {
        this.next = next;
    }
}
