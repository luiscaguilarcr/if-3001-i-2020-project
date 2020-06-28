package edu.ucr.rp.algoritmos.proyecto.logic.tdamethods.implementation;

import edu.ucr.rp.algoritmos.proyecto.logic.domain.AdminAnnotation;
import edu.ucr.rp.algoritmos.proyecto.logic.tdamethods.interfaces.QueueInterface;

public class AdminAnnotationQueue implements QueueInterface {
    public Node front, rear;
    public int accountant = 0;

    public static class Node {
        public AdminAnnotation adminAnnotation;
        public Node nextNode;

        public Node() {
        }

        public Node(AdminAnnotation adminAnnotation) {
            this.adminAnnotation = adminAnnotation;
            this.nextNode = null;
        }
    }

    public AdminAnnotationQueue() {
        front=rear=null;
        accountant=0;
    }

    @Override
    public void enqueue(AdminAnnotation adminAnnotation) {
        Node newNode = new Node(adminAnnotation);
        if(validateEmpty()){ //la cola no existe
            rear = newNode; //encolo por el extremo posterior
            front = rear;
        }else{ //que pasa si ya hay elementos encolados
            rear.nextNode = newNode; //encolo por el extremo posterior
            rear = newNode; //muevo el apuntador a newNode
        }
        //al final actualzo el contador
        accountant++;
    }

    public void clear() {
        front=rear=null;
        accountant=0;
    }

    @Override
    public AdminAnnotation dequeue() {
        if(!validateEmpty()){
            AdminAnnotation element = front.adminAnnotation; //desencolo por el extremo anterior
            //Caso 1. Que pasa si solo hay un elemento encolado
            if(front==rear){
                clear(); //anulo la cola
            }else{ //Caso 2. Caso contrario
                front = front.nextNode; //muevo front al sgte nodo
            }
            //actualizo el contador de elementos
            accountant--;
            return element; //retorno el elemento desencolado
        }
        return null;
    }

    @Override
    public int size() {
        return accountant;
    }

    @Override
    public AdminAnnotationQueue getByID(int iD) {
        AdminAnnotationQueue out = new AdminAnnotationQueue();
        Node actual = front;
        if (front != null) {
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
        Node tempNode = front;
        while (tempNode != null) {
            if (tempNode.adminAnnotation.getCustomerID() == adminAnnotation.getCustomerID() && tempNode.adminAnnotation.getDate().equals(adminAnnotation.getDate())) {
                return true;
            }
            tempNode = tempNode.nextNode;
        }
        return false;
    }
    
    public boolean validateEmpty() {
        return front == null;
    }

    public AdminAnnotation get(int index) {
        int count = 0;
        Node tempNode = front;
        while (tempNode.nextNode != null) {
            if (count == index) {
                return tempNode.adminAnnotation;
            }
            count++;
            tempNode = tempNode.nextNode;
        }
        return null;
    }
}
