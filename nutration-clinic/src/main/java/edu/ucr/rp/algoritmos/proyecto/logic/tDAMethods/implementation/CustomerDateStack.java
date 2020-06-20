/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucr.rp.algoritmos.proyecto.logic.tdamethods.implementation;

import edu.ucr.rp.algoritmos.proyecto.domain.CustomerDate;
import edu.ucr.rp.algoritmos.proyecto.logic.tdamethods.interfaces.StackInterface;


class Node {
    CustomerDate date;
    Node nextNode;
}

public class CustomerDateStack implements StackInterface {
    private Node firstNode;
    private int accountant;

    public CustomerDateStack() {//constructor
        firstNode = null;
        accountant = 0;
    }

    @Override
    public void push(CustomerDate element) {
        Node aux = new Node();
        aux.date = element;

        aux.nextNode = firstNode;
        firstNode = aux;
        accountant++;
    }

    @Override
    public void pop(CustomerDate element) {//remover
        Node current;
        current = firstNode;
        Node past;
        past = null;
        boolean found = false;

        if (firstNode != null) {
            while (current != null && found != true) {

                if (current.date == element) {
                    if (current == firstNode) {
                        firstNode = firstNode.nextNode;
                    } else {
                        past.nextNode = current.nextNode;
                    }
                    //nodo eleimando 
                    accountant--;
                    found = true;

                }
                past = current;
                current = current.nextNode;
            }
            if (!found) {
                System.out.println("\n Nodo no encontrado\n");
            }

        } else {
            System.out.println("La pila esta vacia");
        }
    }

    @Override
    public CustomerDate peek(CustomerDate element) {//seleccionar
        Node current;
        current = firstNode;
        boolean found = false;

        if (firstNode != null) {
            while (current != null && found != true) {
                if (current.date == element) {

                    found = true;
                    return current.date;
                }
                current = current.nextNode;
            }
            if (!found) {
                System.out.println("\n Nodo no encontrado\n");
            }

        } else {
            System.out.println("La �la esta vacia");
        }

        return null;
    }

    @Override
    public int size() {
        return accountant;
    }

    @Override
    public boolean isFull() {
        return accountant > 0;
    }

    @Override
    public boolean isEmpty() {
        return accountant == 0;
    }

    @Override
    public void modify(CustomerDate element, CustomerDate NewElement) {
        Node current;
        current = firstNode;
        boolean found = false;

        if (firstNode != null) {
            while (current != null && found != true) {

                if (current.date == element) {

                    current.date = NewElement;
                    found = true;

                }
                current = current.nextNode;
            }
            if (!found) {
                System.out.println("\n Nodo no encontrado\n");
            }

        } else {
            System.out.println("La pila esta vacia");
        }

    }
}
