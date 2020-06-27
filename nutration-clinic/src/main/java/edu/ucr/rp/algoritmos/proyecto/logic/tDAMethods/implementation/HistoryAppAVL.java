package edu.ucr.rp.algoritmos.proyecto.logic.tdamethods.implementation;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import edu.ucr.rp.algoritmos.proyecto.logic.domain.HistoryApp;

import static java.lang.Integer.max;

//@JsonDeserialize(as = HistoryAppAVL.class)
public class HistoryAppAVL {
    public Node topNode;
    public int targetID = 0;

    public HistoryAppAVL() {
    }

    public static class Node {
        public HistoryApp historyApp;
        public int height, key;
        public Node left, right;

        public Node() {
        }

        /**
         * @param historyApp número que se quiere ingresar en el nodo
         */
        public Node(HistoryApp historyApp) {
            this.historyApp = historyApp;
            this.height = 1;
            this.key = 0;
            this.left = null;
            this.right = null;
        }
    }

    /**
     * Método público que permite el ingreso del valor a un nodo
     *
     * @param value valor que se quiere insertar
     */
    public void insert(HistoryApp value) {
        topNode = insert(topNode, value);
        targetID++;
    }

    /**
     * Método que permite el acceso al método "search"
     *
     * @param key valor que se quiere buscar
     * @return true si existe, si no, false
     */
    public boolean contains(HistoryApp key) {
        Node node = contains(topNode, key);
        if (node != null) {
            return true;
        }
        return false;
    }

    /**
     * Método que permite el acceso al método "deleteNode"
     *
     * @param key valor que se quiere buscar
     * @return verdadero si lo encuentra, si no, falso
     */
    public boolean delete(HistoryApp key) {
        topNode = delete(topNode, key);
        if (topNode != null) {
            return true;
        }
        return false;
    }

    /**
     * Método que permite el acceso al método "printInorder"
     */
    public void printInorder() {
        printInorder(topNode);
    }

    /**
     * Método que permite el acceso al método "printPreorder"
     */
    public void printPreorder() {
        printPreorder(topNode);
    }

    /**
     * Método que inserta un nodo de manera balanceada
     *
     * @param node       raíz del árbol de nodos
     * @param historyApp elemento que se quiere insertar en un nodo
     * @return el último nodo que se agregó
     */
    public Node insert(Node node, HistoryApp historyApp) {
        historyApp.setTargetID(targetIDSize());
        if (targetIDSize() == 0) {
            node = new Node(historyApp);
        } else {
            if (historyApp.getTargetID() % 2 == 0) {
                node.left = insert(node.left, historyApp);
            } else {
                node.right = insert(node.right, historyApp);
            }
        }
        node.height = Integer.max(height(node.left), height(node.right)) + 1;
        int balance = height(node.left) - height(node.right);
        if (balance > 1) { //Desbalance derecho
            if (historyApp.getTargetID() < node.left.historyApp.getTargetID()) { //Caso 1: izquierda-izquierda
                node = rightRotation(node);
            } else { //Caso 2: izquierda-derecha
                node.left = leftRotation(node.left);
                node = rightRotation(node);
            }
        } else if (balance < -1) { //Desbalance izquierdo
            if (historyApp.getTargetID() > node.right.historyApp.getTargetID()) { //Caso 3: derecha-derecha
                node = leftRotation(node);
            } else { //Caso 4: derecha-izquierda
                node.right = rightRotation(node.right);
                node = leftRotation(node);
            }
        }
        return node;
    }

    /**
     * Método recursivo que busca de manera binaria un nodo
     *
     * @param node       raíz del árbol de nodos
     * @param historyApp valor que se quiere buscar
     * @return null si el árbol no tiene nodos, de otro modo, el nodo encontrado
     */
    public Node contains(Node node, HistoryApp historyApp) {
        if (node == null) {
            return null;
        } else {
            if (node.historyApp == historyApp) { //Caso base
                return node;
            } else {
                if (historyApp.getTargetID() < node.historyApp.getTargetID()) { //Caso 1: buscar lado izquierdo
                    node = node.left;
                } else { //Caso 2: buscar l ado derecho
                    node = node.right;
                }
                return contains(node, historyApp);
            }
        }
    }

    /**
     * @param node       raíz del árbol de nodos
     * @param historyApp valor que se quiere eliminar
     * @return verdadero si se elimino, si no, falso
     */
    public Node delete(Node node, HistoryApp historyApp) {
        if (node == null) {
            return null;
        } else {
            if (historyApp.getTargetID() < node.historyApp.getTargetID()) {//busca del lado izquierda
                node.left = delete(node.left, historyApp);
            } else if (historyApp.getTargetID() > node.historyApp.getTargetID()) { //busca del lado derecha
                node.right = delete(node.right, historyApp);
            } else if (node.historyApp == historyApp) { //si encuentra el número
                if (node.left == null && node.right == null) { //Caso 1: nodo sin hijos
                    node = null;
                    return node;
                }//Caso  2: el nodo solo tiene un hijo
                else if (node.left != null && node.right == null) { //el nodo es remplazado por el hijo izquierdo
                    node = node.left;
                    return (node);
                } else if (node.left == null && node.right != null) { //el nodo es remplazado por el hijo derecho
                    node = node.right;
                    return (node);
                } else if (node.left != null && node.right != null) { //Caso 3: el nodo tiene n hijos
                    //obtener el elemento mas pequeño del sub-arbol derecho
                    HistoryApp lowerValue = minValue(node.right);
                    //cambia el nodo raiz con el elemnto obtenido
                    node.historyApp = lowerValue;
                    node.right = delete(node.right, lowerValue);

                    int balance = height(node.left) - height(node.right);
                    if (balance > 1) { //Desbalance derecho
                        if (historyApp.getTargetID() < node.left.historyApp.getTargetID()) { //Caso 1: izquierda-izquierda
                            node = rightRotation(node);
                        } else { //Caso 2: izquierda-derecha
                            node.left = leftRotation(node.left);
                            node = rightRotation(node);
                        }
                    } else if (balance < -1) { //Desbalance izquierdo
                        if (historyApp.getTargetID() > node.right.historyApp.getTargetID()) { //Caso 3: derecha-derecha
                            node = leftRotation(node);
                        } else { //Caso 4: derecha-izquierda
                            node.right = rightRotation(node.right);
                            node = leftRotation(node);
                        }
                    }
                }
            }
            return node;//en cualquier caso retorna el nuevo puntero
        }
    }

    /**
     * Imprime el árbol de nodos en in-order
     *
     * @param node raíz del árbol de nodos
     */
    public void printInorder(Node node) {
        if (node != null) {
            printInorder(node.left); //imprime el hijo izquierdo
            System.out.print(node.historyApp + ", ");
            printInorder(node.right); //imprime el hijo derecho
        }
    }

    /**
     * Imprime el árbol de nodos en pre-order
     *
     * @param node raíz del árbol de nodos
     */
    public void printPreorder(Node node) {
        if (node != null) {
            System.out.print(node.historyApp + ", ");
            printPreorder(node.left); //imprime el hijo izquierda
            printPreorder(node.right); //imprime el hijo derecha
        }
    }

    /**
     * @param node raíz del árbol de nodos
     * @return la altura de una raíz
     */
    public int height(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    /**
     * @return la altura total de un árbol
     */
    public int searchHeight() {
        return height(topNode);
    }

    /**
     * Obtiene el menor número del árbol binario
     *
     * @param node raíz del árbol de nodos
     * @return el número menor o -1 si no existe
     */
    public HistoryApp minValue(Node node) {
        Node tempNode = node;
        while (tempNode.left != null) {
            tempNode = tempNode.left;
        }
        return tempNode.historyApp;
    }

    /**
     * El método cambia los apuntadores del nodo raíz izquierdo con el nodo que ingresa
     *
     * @param node raíz del árbol
     * @return
     */
    public Node rightRotation(Node node) {
        Node lefChild = node.left;
        node.left = lefChild.right;
        lefChild.right = node;
        node.height = max(height(node.left), height(node.right)) + 1;
        lefChild.height = max(height(lefChild.left), height(lefChild.right)) + 1;
        return lefChild;
    }

    /**
     * El método cambia los apuntadores del nodo raíz derecho con el nodo que ingresa
     *
     * @param node raíz del árbol
     * @return
     */
    public Node leftRotation(Node node) {
        Node rightChild = node.right;
        node.right = rightChild.left;
        rightChild.left = node;
        node.height = max(height(node.left), height(node.right)) + 1;
        rightChild.height = max(height(rightChild.left), height(rightChild.right)) + 1;
        return rightChild;
    }

    public int targetIDSize() {
        return targetID;
    }
}
