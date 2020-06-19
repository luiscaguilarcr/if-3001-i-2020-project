package edu.ucr.rp.algoritmos.proyecto.logic.tDAMethods.implementation;

import edu.ucr.rp.algoritmos.proyecto.domain.CustomerDate;
import edu.ucr.rp.algoritmos.proyecto.logic.tDAMethods.interfaces.QueueInterface;

public class AdminAnnotationQueue implements QueueInterface {
    class Node {
        CustomerDate adminAnotation;
        Node nextNode;
    }

    Node firstNode;
    Node lastNode;
    int accountant;

    public AdminAnnotationQueue() {
        accountant = 0;
        firstNode = null;
        lastNode = null;
    }

    @Override
    public void enqueue(CustomerDate adminAnotation) {
        Node aux = new Node();
        aux.adminAnotation = adminAnotation;
        if (firstNode == null) {
            firstNode = aux;
            firstNode.nextNode = null;
            accountant++;
        } else {
            lastNode.nextNode = aux;
            aux.nextNode = null;
            accountant++;
        }
        lastNode = aux;
    }

    @Override
    public void dequeue(CustomerDate adminAnotation) {
        Node actual;
        Node past;
        past = null;
        actual = firstNode;
        boolean found = false;

        if (firstNode != null) {
            while (actual != null && found != true) {
                if (actual.adminAnotation == adminAnotation) {
                    if (actual == firstNode) {
                        firstNode = firstNode.nextNode;
                        accountant--;
                    } else if (actual == lastNode) {
                        past.nextNode = null;
                        lastNode = past;
                        accountant--;
                    } else {
                        past.nextNode = actual.nextNode;
                        accountant--;
                    }
                    System.out.println("\n Cita eliminada \n");
                    found = true;
                }
                past = actual;
                actual = actual.nextNode;
            }
            if (found == false) {
                System.out.println("Cita no fue encontrada ");
            }
        } else {
            System.out.println("Lista de citas esta vacia");
        }
    }

    @Override
    public boolean isEmpty() {
        return firstNode == null;
    }

    @Override
    public int size() {
        return accountant;
    }

    @Override
    public AdminAnnotationQueue getByID(int iD) {
        AdminAnnotationQueue out = new AdminAnnotationQueue();
        Node actual = firstNode;
        if (firstNode != null) {
            while (actual != null) {
                if (actual.adminAnotation.getCustomerID() == iD) {
                    out.enqueue(actual.adminAnotation);
                }
                actual = actual.nextNode;
            }
        }
        return out;
    }
}
