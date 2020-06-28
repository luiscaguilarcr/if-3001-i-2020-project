package edu.ucr.rp.algoritmos.proyecto.logic.tdamethods.implementation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import edu.ucr.rp.algoritmos.proyecto.logic.domain.HistoryApp;
import edu.ucr.rp.algoritmos.proyecto.logic.tdamethods.interfaces.AVLTree;
import edu.ucr.rp.algoritmos.proyecto.logic.tdamethods.nodes.AVLTreeNode;
import edu.ucr.rp.algoritmos.proyecto.util.Utility;

/**
 * @author Luis Carlos
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class HistoryAppAVL implements AVLTree {
    public AVLTreeNode root; //representa la unica entrada al arbol
    public int counter;

    //Constructor
    public HistoryAppAVL() {
        this.root = null;
        counter = 0;
    }

    @Override
    public int size() {
        return counter;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public HistoryApp get(int index) {
        if (!isEmpty()) {
            return binarySearch(root, index);
        }
        return null;
    }

    private HistoryApp binarySearch(AVLTreeNode node, int index) {
        if (node == null) return null;
        else if (Utility.equals(node.historyApp.getTargetID(), index)) {
            return node.historyApp; //YA LO ENCONTRO
        } else if (Utility.lessT(index, node.historyApp.getTargetID()))
            return binarySearch(node.left, index);
        else return binarySearch(node.right, index);
    }

    @Override
    public void add(HistoryApp historyApp) {
        root = add(root, historyApp, "root");
        counter++;
    }

    private AVLTreeNode add(AVLTreeNode node, HistoryApp historyApp, String sequence) {
        if (node == null) { //el arbol esta vacio
            node = new AVLTreeNode(historyApp, "The historyApp " + historyApp
                    + " was inicial added as: " + sequence);
        } else
            //preguntamos si el elemento a insertar es menor o mayor que node.data
            if (Utility.lessT(historyApp.getTargetID(), node.historyApp.getTargetID())) {
                node.left = add(node.left, historyApp, sequence + "/left");
            } else //sino inserta por la der
                if (Utility.greaterT(historyApp.getTargetID(), node.historyApp.getTargetID())) {
                    node.right = add(node.right, historyApp, sequence + "/right");
                }

        //SE DEBE OBTENER EL FACTOR DE EQUILIBRIO
        //PARA VERIFICAR SI EL ARBOL QUEDA BALANCEADO
        //O REQUIERE RE-BALANCEO
        int balance = getBalanceFactor(node);

        //REVISAMOS LA 4 POSIBLES CASOS DE RE-BALANCEO
        //Left-Left Case
        if (balance > 1 && Utility.lessT(historyApp.getTargetID(), node.left.historyApp.getTargetID())) {
            return rightRotate(node);
        }
        //Right Right Case
        if (balance < -1 && Utility.greaterT(historyApp.getTargetID(), node.right.historyApp.getTargetID())) {
            return leftRotate(node);
        }
        //Left Right Case
        //Double rotation
        if (balance > 1 && Utility.greaterT(historyApp.getTargetID(), node.left.historyApp.getTargetID())) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        //Right Left Case
        //Double rotation
        if (balance < -1 && Utility.lessT(historyApp.getTargetID(), node.right.historyApp.getTargetID())) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node; //en todos los casos, retorna un nuevo nodo
    }

    private int getBalanceFactor(AVLTreeNode node) {
        if (node == null) return 0;
        else return height(node.left) - height(node.right);
    }

    private AVLTreeNode leftRotate(AVLTreeNode node) {
        AVLTreeNode node1 = node.right;
        AVLTreeNode node2 = node1.left;
        //se realiza la rotacion
        node1.left = node;
        node.right = node2;
        return node1;
    }

    private AVLTreeNode rightRotate(AVLTreeNode node) {
        AVLTreeNode node1 = node.left;
        AVLTreeNode node2 = node1.right;
        //se realiza la rotacion
        node1.right = node;
        node.left = node2;
        return node1;
    }

    @Override
    public void remove(HistoryApp historyApp) {
        if (!isEmpty()) {
            root = remove(root, historyApp);
        }
    }

    private AVLTreeNode remove(AVLTreeNode node, HistoryApp historyApp) {
        if (node != null) {
            if (Utility.lessT(historyApp.getTargetID(), node.historyApp.getTargetID())) {
                node.left = remove(node.left, historyApp);
            } else if (Utility.greaterT(historyApp.getTargetID(), node.historyApp.getTargetID())) {
                node.right = remove(node.right, historyApp);
            } else if (Utility.equals(node.historyApp.getTargetID(), historyApp)) {
                //CASO 1. EL NODO A SUPRIMIR ES UN NODO SIN HIJOS
                //EN ESTE CASO, EL NODO A SUPRMIR ES UNA HOJA
                if (node.left == null && node.right == null) {
                    return null;
                } else
                    //CASO 2. EL NODO A SUPRIMIR SOLO TIENE UN HIJO
                    //EN ESE CASO, EL NODO A SUPRIMIR CON EL DATA A ELIMINAR
                    //ES REEMPLAZADO POR EL HIJO
                    if (node.left == null && node.right != null) {
                        node = node.right;
                    } else if (node.left != null && node.right == null) {
                        node = node.left;
                    } else
                        //CASO 3 EL NODO A SUPRIMIR TIENE 2 HIJOS
                        if (node.left != null && node.right != null) {
                            //OBTENGA EL VALOR MIN DEL SUBARBOL DERECHO
                            HistoryApp value = min(node.right);
                            node.historyApp = value;
                            node.right = remove(node.right, value);
                        }
            }//equals(node.data, element))
        }//node!=null
        return node;
    }

    public int height() {
        if (!isEmpty()) {
            return height(root) - 1;
        }
        return -1;
    }

    private int height(AVLTreeNode node) {
        if (node == null) return 0;
        else
            return Math.max(
                    height(node.left),
                    height(node.right)) + 1;
    }


    public HistoryApp min() {
        if (!isEmpty()) {
            return min(root);
        }
        return null;
    }

    private HistoryApp min(AVLTreeNode node) {
        if (node.left != null) {
            return min(node.left);
        }
        return node.historyApp;
    }


}
