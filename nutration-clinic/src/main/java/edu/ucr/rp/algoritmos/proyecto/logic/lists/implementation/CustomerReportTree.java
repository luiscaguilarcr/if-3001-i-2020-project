package edu.ucr.rp.algoritmos.proyecto.logic.lists.implementation;

import static java.lang.Integer.max;

class Nodo {

    public Nodo father;
    public Nodo right;
    public Nodo izquierda;
    public int llave;
    public Object contenido;

    //CREAMOS UN CONSTRUCTOR QUE RECIBA UN INDICE PERO NO LOS DEMAS ATRIBUTOS
    public Nodo(int indice) {
        llave = indice;
        right = null;
        izquierda = null;
        father = null;
        contenido = null;
    }

}

public class CustomerReportTree {

    Nodo raiz;

    public CustomerReportTree() {
        raiz = null;
    }

    public void insertar(int i, Object userReport) {
        Nodo node = new Nodo(i);
        node.contenido = userReport;

        //SI LA RAIZ ES NULA SIGNIFICA QUE NO HA EMPEZADO A CRECER EL ARBOL
        if (raiz == null) {
            raiz = node;
        } else {
            // DE LO CONTRARIO CREAMO UN NO AUXILIAR ARA BUSCAR EN DONDE COLOCARLO, SI A LA DER O IZQ
            Nodo aux = raiz;
            //MIENTRAS EL AUXILIAR NO SEA NULO HAGAMOS QUE EL PADRE DEL NODO SEA EL AUXILIAR,
            //ASI VAMOS SUBIENDO EN EL ARBOL
            while (aux != null) {

                node.father = aux;
                //VALIDAMOS, SI EL INDICE ES MAYOR ENTONCES VA A LA DERECHA
                if (node.llave >= aux.llave) {
                    aux = aux.right;
                } else {
                    //SI NO VA A LA IZQUIERDA
                    aux = aux.izquierda;
                }
            }
            //si la llave del nodo actual es menor al indice del nodo padre entonces enlazo la direccion
            //dentro del arbol
            if (node.llave < node.father.llave) {
                node.father.izquierda = node;
            } else {
                node.father.right = node;
            }
        }
    }

    //CREAREMOS UN METODO PRA RECORRER inorder LA RAMA IZQUIERDA Y LUEGO LA DERECHA PARA
    //PARA ORDENAR LOS INDICES DE CADA NODO
    public void recorrer(Nodo node) {
        if (node != null) {
            recorrer(node.izquierda);
            System.out.println("Indice " + node.llave + " userTReport" + node.contenido);
            recorrer(node.right);
        }
    }

}// end 

