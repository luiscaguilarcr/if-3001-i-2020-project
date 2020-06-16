/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucr.rp.algoritmos.proyecto.logic.service.implementation;

import edu.ucr.rp.algoritmos.proyecto.logic.lists.interfaces.StackInterface;

/**
 *
 * @author Alvaro Miranda Cort
 */
public class DailyDatesStack implements StackInterface {
 
    private int arr[];
    private int top;
    private int capacity;

   
     DailyDatesStack(int Capacity) {//constructor
        arr = new int[Capacity];
        capacity = Capacity;
        top = -1;

    }

    @Override
    public void push(int x) {
        if (!isFull()) {
            arr[++top] = x;
        }//if
    }

    @Override
    public Object pop() {
     if(isEmpty())
                  return null;
              else
            return arr[top--];
    }

    @Override
    public Object peek() {
    if(!isEmpty())
        return arr[top];
        else
            return null;
    }

    @Override
    public int size() {
     return top + 1;
    }

    @Override
    public boolean isFull() {
         return top == capacity - 1;
    }

    @Override
    public boolean isEmpty() {
      return top == -1;
    }
}