package edu.ucr.rp.algoritmos.proyecto.domain;

public class AnnotationAdmin {
    private Object weight, fat, height;
    private int customerID, docID;

    public Object getWeight() {
        return weight;
    }

    public AnnotationAdmin setWeight(Object weight) {
        this.weight = weight;
        return this;
    }

    public Object getFat() {
        return fat;
    }

    public AnnotationAdmin setFat(Object fat) {
        this.fat = fat;
        return this;
    }

    public Object getHeight() {
        return height;
    }

    public AnnotationAdmin setHeight(Object height) {
        this.height = height;
        return this;
    }

    public int getCustomerID() {
        return customerID;
    }

    public AnnotationAdmin setCustomerID(int customerID) {
        this.customerID = customerID;
        return this;
    }

    public int getDocID() {
        return docID;
    }

    public AnnotationAdmin setDocID(int docID) {
        this.docID = docID;
        return this;
    }
}
