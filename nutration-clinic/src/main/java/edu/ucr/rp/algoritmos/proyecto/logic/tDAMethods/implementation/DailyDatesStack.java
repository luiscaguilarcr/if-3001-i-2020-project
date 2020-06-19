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
    Node next;

}

public class DailyDatesStack implements StackInterface {

    private Node first;
    private int accountant;

    public DailyDatesStack() {//constructor
        first = null;
        accountant = 0;
    }

    @Override
    public void push(CustomerDate element) {
        Node aux = new Node();
        aux.date = element;

        aux.next = first;
        first = aux;
        accountant++;
    }

    @Override
    public void pop(CustomerDate element) {//remover
        Node current;
        current = first;
        Node past;
        past = null;
        boolean found = false;

        if (first != null) {
            while (current != null && found != true) {

                if (current.date == element) {
                    if (current == first) {
                        first = first.next;
                    } else {
                        past.next = current.next;
                    }
                    //nodo eleimando 
                    accountant--;
                    found = true;

                }
                past = current;
                current = current.next;
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
        current = first;
        boolean found = false;

        if (first != null) {
            while (current != null && found != true) {
                if (current.date == element) {

                    found = true;
                    return current.date;
                }
                current = current.next;
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
        current = first;
        boolean found = false;

        if (first != null) {
            while (current != null && found != true) {

                if (current.date == element) {

                    current.date = NewElement;
                    found = true;

                }
                current = current.next;
            }
            if (!found) {
                System.out.println("\n Nodo no encontrado\n");
            }

        } else {
            System.out.println("La pila esta vacia");
        }

    }
}
