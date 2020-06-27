package edu.ucr.rp.algoritmos.proyecto.logic.tdamethods.implementation;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import edu.ucr.rp.algoritmos.proyecto.logic.domain.CustomerDate;

/**
 * @author Luis Carlos
 */

public class CustomerReportTree {
    public int targetID = 0;
    public Node topNode;     //puntero al nodo raiz del arbol sera nulo en un arbol vacio

    public CustomerReportTree() {
    }

    public static class Node {
        public CustomerDate customerDate;
        public Node leftNode, rightNode;

        /**
         * Agrega en un nuevo nodo el elemento
         *
         * @param customerDate número que se quiere agregar
         */
        public Node(CustomerDate customerDate) {
            this.customerDate = customerDate;
            leftNode = null;
            rightNode = null;
        }
    }


    // metodos publicos
    public void insert(CustomerDate customerDate) {
        topNode = insert(topNode, customerDate);
    }

    public boolean search(CustomerDate customerDate) {
        return search(topNode, customerDate);
    }

    public CustomerDate get(int index) {
        return get(topNode, index);
    }

    public void delete(CustomerDate customerDate) {
        topNode = delete(topNode, customerDate);
    }

    public CustomerDate minValue() {
        return minValue(topNode);
    }

    public void preOrder() {
        preOrder(topNode);
    }

    public void postOrder() {
        postOrder(topNode);
    }

    public void inOrder() {
        inOrder(topNode);
    }

    /**
     * Inserta un customerDate en un árbol binario
     *
     * @param node         objeto del árbol binario
     * @param customerDate customerDate que se desea agregar en el árbol binario
     * @return árbol de nodos
     */
    private Node insert(Node node, CustomerDate customerDate) {
        Node newNode = new Node(customerDate);
        if (node == null) { //si el árbol está vacío
            node = newNode;
            topNode = node; //agrega el nuevo node en la raíz
        } else { //si no
            if (customerDate.getTargetID() < node.customerDate.getTargetID()) { //agregar en el hijo izquierda
                node.leftNode = insert(node.leftNode, customerDate);
            } else { //agregar en el hijo derecha
                node.rightNode = insert(node.rightNode, customerDate);
            }
        }
        return node;
    }

    /**
     * @param node         objeto del árbol binario
     * @param customerDate customerDate que queremos buscar en el árbol binario
     * @return verdadero si lo encontró, si no falso
     */
    private boolean search(Node node, CustomerDate customerDate) {
        if (node != null) {
            if (customerDate.getTargetID() < node.customerDate.getTargetID()) {
                if (node.customerDate == customerDate) {
                    return true;
                } else {
                    return search(node.leftNode, customerDate);
                }
            } else {
                if (node.customerDate == customerDate) {
                    return true;
                } else {
                    return search(node.rightNode, customerDate);
                }
            }
        }
        return false;
    }

    /**
     * @param node               objeto del árbol binario
     * @param customerDateTarget customerDateTarget que queremos buscar en el árbol binario
     * @return verdadero si lo encontró, si no falso
     */
    private CustomerDate get(Node node, int customerDateTarget) {
        if (node != null) {
            if (customerDateTarget < node.customerDate.getTargetID()) {
                if (node.customerDate.getTargetID() == customerDateTarget) {
                    return node.customerDate;
                } else {
                    return get(node.leftNode, customerDateTarget);
                }
            } else {
                if (node.customerDate.getTargetID() == customerDateTarget) {
                    return node.customerDate;
                } else {
                    return get(node.rightNode, customerDateTarget);
                }
            }
        }
        return null;
    }

    /**
     * Elimina un elemento del árbol binario
     *
     * @param node         objeto del árbol binario
     * @param customerDate customerDate que se quiere eliminar
     * @return árbol binario
     */
    private Node delete(Node node, CustomerDate customerDate) {
        if (customerDate.getTargetID() < node.customerDate.getTargetID()) {//busca del lado izquierda
            node.leftNode = delete(node.leftNode, customerDate);
        } else if (customerDate.getTargetID() > node.customerDate.getTargetID()) { //busca del lado derecha
            node.rightNode = delete(node.rightNode, customerDate);
        } else if (node.customerDate == customerDate) { //si encuentra el customerDate
            //Caso 1: node sin hijos
            if (node.leftNode == null && node.rightNode == null) {
                node = null;
                return node;
            } //Caso  2: el node solo tiene un hijo
            //el node es remplazado por el hio
            else if (node.leftNode != null && node.rightNode == null) {
                node = node.leftNode;
                return (node);
            } else if (node.leftNode == null && node.rightNode != null) {
                node = node.rightNode;
                return (node);
            } //Caso 3: el node tiene hijos
            else if (node.leftNode != null && node.rightNode != null) {
                //obtener el elemento mas pequeño del sub-arbol derecha
                CustomerDate rightSubTree = minValue(node.rightNode);
                //cambia el node raiz con el elemnto obtenido
                node.customerDate = rightSubTree;
                node.rightNode = delete(node.rightNode, rightSubTree);
            }// n fin de nodos con de hjos
        }
        return node;//en cualquier caso retorna el nuevo puntero

    }

    /**
     * Obtiene el menor número del árbol binario
     *
     * @param nodo objeto del árbol binario
     * @return el menor número o -1 si no existe
     */
    private CustomerDate minValue(Node nodo) {
        Node tempNode = nodo;
        while (tempNode.leftNode != null) {
            tempNode = tempNode.leftNode;
        }
        return tempNode.customerDate;
    }

    /**
     * Lee un árbol binario en PreOrden: 1.Visita la raíz, 2.Atraviesa el
     * sub-árbol izquierda, 3.Atraviesa el sub-árbol derecha
     *
     * @param nodo nodo del árbol binario
     */
    private void preOrder(Node nodo) {
        if (nodo != null) {
            System.out.print(nodo.customerDate + ", ");
            preOrder(nodo.leftNode); //imprime el hijo izquierda
            preOrder(nodo.rightNode); //imprime el hijo derecha
        }
    }

    /**
     * Lee un árbol binario en InOrden: 1.Atraviesa el sub-árbol izquierda,
     * 2.Visita la raíz, 3.Atraviesa el sub-árbol derecha
     *
     * @param nodo objeto del árbol binario
     */
    private void inOrder(Node nodo) {
        if (nodo != null) {
            inOrder(nodo.leftNode); //imprime el hijo izquierda
            System.out.print(nodo.customerDate + ", ");
            inOrder(nodo.rightNode); //imprime el hijo derecha
        }
    }

    /**
     * Lee un árbol binario en la siguiente secuencia: 1.Atraviesa el sub-árbol
     * izquierda, 2.Atraviesa el sub-árbol derecha, 3.Visita la raíz
     *
     * @param nodo objeto del árbol binario
     */
    private void postOrder(Node nodo) {
        if (nodo != null) {
            postOrder(nodo.leftNode); //imprime el hijo izquierda
            postOrder(nodo.rightNode); //imprime el hijo derecha
            System.out.print(nodo.customerDate + ", ");
        }
    }

    public int size() {
        return targetID;
    }
}
