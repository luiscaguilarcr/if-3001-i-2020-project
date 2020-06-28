package edu.ucr.rp.algoritmos.proyecto.logic.tdamethods.implementation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import edu.ucr.rp.algoritmos.proyecto.logic.domain.CustomerDate;
import edu.ucr.rp.algoritmos.proyecto.logic.tdamethods.interfaces.Stack;
import edu.ucr.rp.algoritmos.proyecto.logic.tdamethods.nodes.StackNode;

/**
 * @author Luis Carlos
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerDateStack implements Stack {
    public StackNode top; //un apuntador al tope de la pila
    public int count; //contador de elementos

    //Constructor
    public CustomerDateStack() {
        top = null; //al apuntador a nulo
        count = 0;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean isEmpty() {
        return top == null;
    }

    @Override
    public void push(CustomerDate element) {
        StackNode newNode = new StackNode(element);
        if (isEmpty()) {
            top = newNode;
        } else {
            newNode.next = top;
            top = newNode;
        }
        count++; //incremento el contador de elementos
    }

    @Override
    public CustomerDate pop() {
        if (!isEmpty()) {
            CustomerDate element = top.data;
            top = top.next; //lo movemos al siguiente nodo
            count--;
            return element;
        }
        return null;
    }

}