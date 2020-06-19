package edu.ucr.rp.algoritmos.proyecto.logic.tDAMethods.implementation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import edu.ucr.rp.algoritmos.proyecto.domain.User;
import edu.ucr.rp.algoritmos.proyecto.logic.tDAMethods.interfaces.LinkedListInterface;

/**
 * @author Noel
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserLinkedList implements LinkedListInterface {
    public Node firstNode, node;
    //To generate a node object
    public UserLinkedList(){}
    public static class Node {
        public Node nextNode;
        public User user;

        public Node() {
        }

        public Node(User user) {
            this.user = user;
        }
    }

    /**
     * Agrega un usuario en una lista enlazada de nodos.
     *
     * @param user que se quiere agregar
     */
    @Override
    public void add(User user) {
        //Crear un nuevo nodo a partir del valor ingresado
        Node newNode = new Node(user);
        if (node == null) { //Case 1: si la lista está vacía
            node = newNode; //se deja una referencia del último nodo agregado
            firstNode = node; //Posiciona el nuevo nodo en la primera posición
        } else { //Case 2: la lista tiene al menos un nodo
            node.nextNode = newNode; //se le otorga al último nodo ingresado un apuntador del nuevo nodo
            node = newNode; //se deja una referencia del último nodo agregado
        }
    }

    /**
     * Remueve un usuario en una lista enlazada de nodos.
     *
     * @param user que se quiere remover
     */
    @Override
    public void remove(User user) {
        // Store head node
        Node tempNode = firstNode, previousNode = null;
        // If head node itself holds the key to be deleted
        if (tempNode != null && tempNode.user.getiD() == user.getiD()) {
            firstNode = tempNode.nextNode; // Changed head
            return;
        }
        // Search for the key to be deleted, keep track of the
        // previous node as we need to change tempNode.next
        while (tempNode != null && tempNode.user.getiD() != user.getiD()) {
            previousNode = tempNode;
            tempNode = tempNode.nextNode;
        }
        // If key was not present in linked list
        if (tempNode == null) return;
        // Unlink the node from linked list
        previousNode.nextNode = tempNode.nextNode;
    }


    /**
     * Obtiene la longitud de la lista.
     *
     * @return longitud de la lista
     */
    @Override
    public int size() {
        Node tempNode = firstNode; //nodo temporal en la primera posición
        int accountant = 0;

        if (tempNode != null) {
            do {
                accountant++;   //suma 1 al contador
                tempNode = tempNode.nextNode;//avanza una posición en la lista
            } while (tempNode != null);
        }
        return accountant;
    }

    /**
     * Obtiene el índice de un usuario.
     *
     * @param user que se quiere buscar
     * @return índice del elemento ingresado
     */
    @Override
    public int indexOf(User user) {
        Node tempNode = firstNode;
        if (!contains(user)) {
            return -1;
        }
        int index = 0;
        do {
            if (tempNode.user == user) {
                return index;
            }
            index++;
            tempNode = tempNode.nextNode;
        } while (tempNode != null);
        return index;
    }

    /**
     * Obtiene el objeto en el índice solicitado.
     *
     * @param index del elemento que se quiere obtener
     * @return elemento de la lista
     */
    @Override
    public User get(int index) {
        int accountant = 0; //contador para la posición en el índice
        Node tempNode = firstNode; //posicionar un nodo temporal en el primer elemento
        if (!isEmpty()) { //Caso 1: la lista tiene al menos un número
            while (tempNode != null) {
                if (accountant == index) {
                    return tempNode.user;//devolver el número en el índice pedido
                }
                accountant++;
                tempNode = tempNode.nextNode; //avanzar una posición en la lista
            }
        }
        return null; //no lo encontró
    }

    /**
     * Verifica si el elemento ingresado es contenido por la lista.
     *
     * @param user a evaluar
     * @return true si lo contine, de lo contrario, false
     */
    @Override
    public boolean contains(User user) {
        if (isEmpty()) { //Case 1: the list is empty
            return false;
        }
        //The temporary node is positioned on the first node
        Node tempNode = firstNode;
        //Loops through the list of nodes
        while (tempNode != null) {
            //When the item to find is found, enter it in the if
            if (user.getiD() == tempNode.user.getiD()) {
                return true;
            }
            //Advance one position in the node list
            tempNode = tempNode.nextNode;
        }
        return false;
    }

    /**
     * Valida si la lista está limpia.
     *
     * @return true si la lista está vacía, si no, false
     */
    @Override
    public boolean isEmpty() {
        return firstNode == null;
    }

    public void clear(){
        firstNode = null;
    }

    /**
     * Para pruebas
     *
     * @param user que se quiere imprimir
     */
    public void print(User user) {
        System.out.println(user.toString());
    }
}
