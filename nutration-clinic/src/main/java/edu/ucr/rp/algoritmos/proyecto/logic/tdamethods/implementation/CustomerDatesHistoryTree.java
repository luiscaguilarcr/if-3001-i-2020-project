package edu.ucr.rp.algoritmos.proyecto.logic.tdamethods.implementation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import edu.ucr.rp.algoritmos.proyecto.logic.domain.CustomerDate;
import edu.ucr.rp.algoritmos.proyecto.logic.tdamethods.interfaces.BTree;
import edu.ucr.rp.algoritmos.proyecto.logic.tdamethods.nodes.TreeNode;
import edu.ucr.rp.algoritmos.proyecto.util.Utility;

/**
 * @author Luis Carlos
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerDatesHistoryTree implements BTree {
    public TreeNode root; //representa la unica entrada al arbol

    //Constructor
    public CustomerDatesHistoryTree() {
        this.root = null;
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(TreeNode node) {
        if (node == null) return 0;
        else
            return 1 + size(node.left) + size(node.right);
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public CustomerDate get(int index) {
        if (!isEmpty()) {
            return binarySearch(root, index);
        }
        return null;
    }

    private CustomerDate binarySearch(TreeNode node, int index) {
        if (node == null) return null;
        else if (node.customerDate.getTargetID() == index) {
            return node.customerDate; //YA LO ENCONTRO
        } else if (node.customerDate.getTargetID() < index) {
            return binarySearch(node.left, index);
        } else {
            return binarySearch(node.right, index);
        }
    }

    @Override
    public void add(CustomerDate element) {
        element.setTargetID(size());
        root = add(root, element, "root", 0);
    }

    private TreeNode add(TreeNode node, CustomerDate customerDate) {
        if (node == null) { //el arbol esta vacio
            node = new TreeNode(customerDate);
        } else if (node.left == null) {
            node.left = add(node.left, customerDate);
        } else if (node.right == null) {
            node.right = add(node.right, customerDate);
        } else { //debemos establecer algun criterio de insercion
            //con un criterio aletario, decide como agregar
            //el nuevo elemento
            int num = Utility.random(10);
            if (num % 2 == 0) {//si es par, inserta por la izq
                node.left = add(node.left, customerDate);
            } else //sino inserta por la der
                node.right = add(node.right, customerDate);
        }
        return node;
    }

    private TreeNode add(TreeNode node, CustomerDate customerDate, String label, int level) {
        if (node == null) { //el arbol esta vacio
            node = new TreeNode(customerDate, label, level);
        } else if (node.left == null) {
            node.left = add(node.left, customerDate, label + "/left", ++level);
        } else if (node.right == null) {
            node.right = add(node.right, customerDate, label + "/right", ++level);
        } else { //debemos establecer algun criterio de insercion
            //con un criterio aletario, decide como agregar
            //el nuevo elemento
            int num = Utility.random(10);
            if (num % 2 == 0) {//si es par, inserta por la izq
                node.left = add(node.left, customerDate, label + "/left", ++level);
            } else //sino inserta por la der
                node.right = add(node.right, customerDate, label + "/right", ++level);
        }
        return node;
    }

    @Override
    public void remove(CustomerDate customerDate) {
        if (!isEmpty()) {
            root = remove(root, customerDate);
        }
    }

    private TreeNode remove(TreeNode node, CustomerDate customerDate) {
        if (node != null) {
            if (Utility.equals(node.customerDate, customerDate)) {
                //CASO 1. EL NODO A SUPRIMIR ES UN NODO SIN HIJOS
                //EN ESTE CASO, EL NODO A SUPRMIR ES UNA HOJA
                if (node.left == null && node.right == null) {
                    return node = null;
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
                            //OBTENGA UNA HOJA DEL SUBARBOL DERECHO
                            CustomerDate value = getLeaf(node.right);
                            node.customerDate = value;
                            node.right = removeLeaf(node.right, value);
                        }
            }//equals(node.data, element))
            node.left = remove(node.left, customerDate);
            node.right = remove(node.right, customerDate);
        }//node!=null
        return node;
    }

    private CustomerDate getLeaf(TreeNode node) {
        CustomerDate aux;
        if (node == null) return null;
        else if (node.left == null && node.right == null) {
            return node.customerDate; //es una hoja
        } else {
            aux = getLeaf(node.left);
            if (aux == null) {
                //quiere decir q todavia no ha encontrado una hoja
                aux = getLeaf(node.right);
            }
        }
        return aux;
    }

    private TreeNode removeLeaf(TreeNode node, CustomerDate customerDate) {
        if (node == null) return null;
        else if (node.left == null && node.right == null
                && Utility.equals(node.customerDate, customerDate)) {
            //ES UNA HOJA Y ES LA QUE ANDO BUSCANDO,
            //YA LA PUEDO ELIMINAR
            return null;
        } else {
            node.left = removeLeaf(node.left, customerDate);
            node.right = removeLeaf(node.right, customerDate);
        }
        return node;
    }

}
