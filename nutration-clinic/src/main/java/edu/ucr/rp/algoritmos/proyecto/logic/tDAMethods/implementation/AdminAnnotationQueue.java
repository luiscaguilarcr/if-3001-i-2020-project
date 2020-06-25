package edu.ucr.rp.algoritmos.proyecto.logic.tdamethods.implementation;

import edu.ucr.rp.algoritmos.proyecto.logic.domain.AdminAnnotation;
import edu.ucr.rp.algoritmos.proyecto.logic.tdamethods.interfaces.QueueInterface;

public class AdminAnnotationQueue implements QueueInterface {
    public class Node {
        AdminAnnotation adminAnnotation;
        Node nextNode;
    }

    public Node firstNode;
    public Node lastNode;
    int accountant;

    public AdminAnnotationQueue() {
        accountant = 0;
        firstNode = null;
        lastNode = null;
    }

    @Override
    public void enqueue(AdminAnnotation adminAnnotation) {
        Node aux = new Node();
        aux.adminAnnotation = adminAnnotation;
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
    public void dequeue(AdminAnnotation adminAnnotation) {
        Node actual;
        Node past;
        past = null;
        actual = firstNode;
        boolean found = false;

        if (firstNode != null) {
            while (actual != null && found != true) {
                if (actual.adminAnnotation.equals(adminAnnotation)) {
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
    public int size() {
        return accountant;
    }

    @Override
    public AdminAnnotationQueue getByID(int iD) {
        AdminAnnotationQueue out = new AdminAnnotationQueue();
        Node actual = firstNode;
        if (firstNode != null) {
            while (actual != null) {
                if (actual.adminAnnotation.getCustomerID() == iD) {
                    out.enqueue(actual.adminAnnotation);
                }
                actual = actual.nextNode;
            }
        }
        return out;
    }

    @Override
    public boolean contains(AdminAnnotation adminAnnotation) {
        Node tempNode = firstNode;
        while (tempNode != null) {
            if (tempNode.adminAnnotation.getCustomerID() == adminAnnotation.getCustomerID() && tempNode.adminAnnotation.getDate().equals(adminAnnotation.getDate())) {
                return true;
            }
            tempNode = tempNode.nextNode;
        }
        return false;
    }

    public boolean validateEmpty() {
        return firstNode == null;
    }

    public AdminAnnotation get(int index) {
        int accountant = 0;
        Node tempNode = firstNode;
        while (tempNode.nextNode != null) {
            if (accountant == index) {
                return tempNode.adminAnnotation;
            }
            accountant++;
            tempNode = tempNode.nextNode;
        }
        return null;
    }
}
