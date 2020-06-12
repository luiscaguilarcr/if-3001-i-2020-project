package edu.ucr.rp.algoritmos.proyecto.logic.lists.implementation;

import edu.ucr.rp.algoritmos.proyecto.domain.User;
import edu.ucr.rp.algoritmos.proyecto.logic.lists.interfaces.LinkedListInterface;

/**
 * @author Noel
 */
public class LinkedList implements LinkedListInterface {
    private Node firstNode, node;

    //To generate a node object
    class Node {
        Node previousNode;
        Node nextNode;
        User user;
        public Node(User user) {
            this.user = user;
        }
    }

    /**
     * Agrega un usuario en una lista enlazada de nodos.
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
            newNode.previousNode = node; //se le otorga un puntero anterior al nuevo nodo en el último nodo agregado
            node = newNode; //se deja una referencia del último nodo agregado
        }
    }

    /**
     * Remueve un usuario en una lista enlazada de nodos.
     * @param user que se quiere remover
     */
    @Override
    public void remove(User user) {
        Node tempNode = firstNode; //This variable saves in a temporal node the node.
        while (tempNode != null) {
            if (tempNode.user == user) {
                if (tempNode.nextNode != null) {    //Case #1: The second node is removed
                    tempNode.nextNode.previousNode = tempNode.previousNode;   //Assigns the previous node of the node that was deleted to a temporary variable.
                    if (tempNode.previousNode != null) {
                        tempNode.previousNode.nextNode = tempNode.nextNode;        //Assigns the next node of the node that was deleted to a temporary variable
                    }//End if
                } else {    //Case #2: The last node is removed.
                    tempNode.previousNode.nextNode = null;
                }//End if/else
                if (tempNode.previousNode != null) {    //Case #3: The third node is removed
                    tempNode.previousNode.nextNode = tempNode.nextNode; //Relates the previous node to the next node from which it was removed.
                    if (tempNode.nextNode != null) {
                        tempNode.nextNode.previousNode = tempNode.previousNode;
                    }//End if
                } else {    //Case #4: The first node is removed.
                    firstNode = tempNode.nextNode;
                    tempNode.nextNode.previousNode = null;
                }//End if/else
            }//End if(tempNode.value == value)
            tempNode = tempNode.nextNode;
        }//End while()
    }

    /**
     * Obtiene la longitud de la lista enlazada doble de usuarios.
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
     * @param user que se quiere buscar
     * @return índice del elemento ingresado
     */
    @Override
    public int indexOf(User user) {
        Node tempNode = firstNode;
        if(!contains(user)){
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
     * @param index del elemento que se quiere obtener
     * @return elemento de la lista
     */
    @Override
    public User getAt(int index) {
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
            if (user == tempNode.user) {
                return true;
            }
            //Advance one position in the node list
            tempNode = tempNode.nextNode;
        }
        return false;
    }

    /**
     * Valida si la lista está limpia.
     * @return true si la lista está vacía, si no, false
     */
    @Override
    public boolean isEmpty() {
        return firstNode == null;
    }

    /**
     * Para pruebas
     * @param user que se quiere imprimir
     */
    public void print(User user) {
        System.out.println(user.toString());
    }
}
