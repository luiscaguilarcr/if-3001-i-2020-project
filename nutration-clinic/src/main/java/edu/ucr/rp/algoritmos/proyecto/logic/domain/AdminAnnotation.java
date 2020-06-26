package edu.ucr.rp.algoritmos.proyecto.logic.domain;

public class AdminAnnotation {
    private Object weight, fat, height;
    private int customerID, docID;
    private String date;
    private EatingPlan eatingPlan;

    public Object getWeight() {
        return weight;
    }

    public AdminAnnotation setWeight(Object weight) {
        this.weight = weight;
        return this;
    }

    public Object getFat() {
        return fat;
    }

    public AdminAnnotation setFat(Object fat) {
        this.fat = fat;
        return this;
    }

    public Object getHeight() {
        return height;
    }

    public AdminAnnotation setHeight(Object height) {
        this.height = height;
        return this;
    }

    public int getCustomerID() {
        return customerID;
    }

    public AdminAnnotation setCustomerID(int customerID) {
        this.customerID = customerID;
        return this;
    }

    public int getDocID() {
        return docID;
    }

    public AdminAnnotation setDocID(int docID) {
        this.docID = docID;
        return this;
    }

    public String getDate() {
        return date;
    }

    public AdminAnnotation setDate(String date) {
        this.date = date;
        return this;
    }

    public EatingPlan getEatingPlan() {
        return eatingPlan;
    }

    public AdminAnnotation setEatingPlan(EatingPlan eatingPlan) {
        this.eatingPlan = eatingPlan;
        return this;
    }
}
