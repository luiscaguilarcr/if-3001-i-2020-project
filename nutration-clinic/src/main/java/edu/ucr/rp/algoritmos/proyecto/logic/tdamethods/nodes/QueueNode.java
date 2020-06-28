package edu.ucr.rp.algoritmos.proyecto.logic.tdamethods.nodes;

import edu.ucr.rp.algoritmos.proyecto.logic.domain.AdminAnnotation;

/**
 * @author Luis Carlos
 */
public class QueueNode {
    public AdminAnnotation adminAnnotation; //objeto almacenado
    public int priority; //1=low, 3=high
    public QueueNode next; //apuntador al sgte nodo

    //Constructor
    public QueueNode(AdminAnnotation adminAnnotation) {
        this.adminAnnotation = adminAnnotation;
        this.next = null;
    }

    //Constructor sobrecargado
    public QueueNode() {
        this.next = null;
    }

    public Object getAdminAnnotation() {
        return adminAnnotation;
    }

    public void setAdminAnnotation(AdminAnnotation adminAnnotation) {
        this.adminAnnotation = adminAnnotation;
    }

    public QueueNode getNext() {
        return next;
    }

    public void setNext(QueueNode next) {
        this.next = next;
    }
}
