/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucr.rp.algoritmos.proyecto.util.files.backUp;

import edu.ucr.rp.algoritmos.proyecto.logic.domain.CustomerDate;

public class CustomerDateStack implements StackInterface {
    public Node firstNode;
    public int accountant;

    public static class Node {
        public CustomerDate date;
        public Node nextNode;
    }

    public CustomerDateStack() {//constructor
        firstNode = null;
        accountant = 0;
    }

    @Override
    public void push(CustomerDate customerDate) {
        Node tempNode = new Node();
        tempNode.date = customerDate;

        tempNode.nextNode = firstNode;
        firstNode = tempNode;
        accountant++;
    }

    @Override
    public void pop(CustomerDate customerDate) {//remover
        Node current;
        current = firstNode;
        Node past;
        past = null;
        boolean found = false;

        if (firstNode != null) {
            while (current != null && found != true) {

                if (current.date.getCustomerID() == customerDate.getCustomerID()) {
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
    public CustomerDate peek(CustomerDate customerDate) {//seleccionar
        Node current;
        current = firstNode;
        boolean found = false;

        if (firstNode != null) {
            while (current != null && found != true) {
                if (current.date == customerDate) {

                    found = true;
                    return current.date;
                }
                current = current.nextNode;
            }
            if (!found) {
                System.out.println("\n Nodo no encontrado\n");
            }
        } else {
            System.out.println("La pila la esta vacia");
        }

        return null;
    }

    @Override
    public int size() {
        return accountant;
    }

    @Override
    public boolean validateFill() {
        return accountant > 0;
    }

    @Override
    public boolean validateEmpty() {
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

    /**
     * Valida la existencia de una cita
     * @param customerDate que se quiere validar
     * @return true si la pila contiene la cita, si no, false
     */
    public boolean contains(CustomerDate customerDate){
        Node tempNode = firstNode;
        while (tempNode != null){
            if(tempNode.date.getCustomerID() == customerDate.getCustomerID()){
                return true;
            }
            tempNode = tempNode.nextNode;
        }
        return false;
    }

    public CustomerDate getByAcc(int index){
        Node tempNode = firstNode;
        int accountant  = 0;
        while (tempNode != null){
            if(accountant == index){
                return tempNode.date;
            }
            accountant++;
            tempNode = tempNode.nextNode;
        }
        return null;
    }
}
