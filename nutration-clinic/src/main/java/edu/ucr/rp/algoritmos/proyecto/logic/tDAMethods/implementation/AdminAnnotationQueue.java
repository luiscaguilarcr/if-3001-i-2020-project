package edu.ucr.rp.algoritmos.proyecto.logic.tdamethods.implementation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import edu.ucr.rp.algoritmos.proyecto.logic.domain.AdminAnnotation;
import edu.ucr.rp.algoritmos.proyecto.logic.tdamethods.interfaces.Queue;
import edu.ucr.rp.algoritmos.proyecto.logic.tdamethods.nodes.QueueNode;
import edu.ucr.rp.algoritmos.proyecto.util.Utility;

/**
 * @author Luis Carlos
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdminAnnotationQueue implements Queue {
    public QueueNode front; //apuntador al anterior/frente de la cola
    public QueueNode rear; //apuntador al posterior/final de la cola
    public int count; //control de elementos encolados

    //Cosntructor
    public AdminAnnotationQueue() {
        front = rear = null;
        count = 0;
    }

    @Override
    public int size() {
        return this.count;
    }

    @Override
    public boolean isEmpty() {
        return front == null;
    }

    @Override
    public void enQueue(AdminAnnotation element) {
        QueueNode newNode = new QueueNode(element);
        if (isEmpty()) { //la cola no existe
            rear = newNode; //encolo por el extremo posterior
            //garantizo que anterior quede apuntando al primer nodo
            front = rear;
        } else { // pasa si ya hay elementos encolados
            rear.next = newNode; //encolo por el extremo posterior
            rear = newNode; //muevo el apuntador a newNode
        }
        //al final actualzo el contador
        this.count++;
    }

    @Override
    public AdminAnnotation deQueue() {
        if (!isEmpty()) {
            AdminAnnotation element = front.adminAnnotation; //desencolo por el extremo anterior
            //Caso 1: pasa si solo hay un elemento encolado
            if (front == rear) {
                front = rear = null;
                count = 0;
            } else { //Caso 2: Caso contrario
                front = front.next; //muevo front al sgte nodo
            }
            //actualizo el contador de elementos
            count--;
            return element; //retorno el elemento desencolado
        }
        return null;
    }

    @Override
    public boolean contains(AdminAnnotation element) {
        if (!isEmpty()) {
            AdminAnnotationQueue aux = new AdminAnnotationQueue();
            boolean finded = false; //encontrado
            while (!isEmpty()) {
                if (Utility.equals(front(), element)) {
                    finded = true; //ya lo encontro
                }
                aux.enQueue(deQueue());
            }
            //al final dejamos la cola en su estado original
            while (!aux.isEmpty()) {
                enQueue(aux.deQueue());
            }
            return finded;
        }
        return false;
    }

    @Override
    public AdminAnnotation front() {
        if (!isEmpty()) {
            return front.adminAnnotation;
        }
        return null;
    }

}
