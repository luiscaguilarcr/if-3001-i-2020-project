package edu.ucr.rp.algoritmos.proyecto.logic.domain;

public class AdminAnnotation {
    private int weight, fat, height;//peso, grasa, altura
    private int customerID, docID;
    private String date;// fecha anotacion 
    private EatingPlan eatingPlan;//combo box 

    public int  getWeight() {
        return weight;
    }

    public AdminAnnotation setWeight( int weight) {
        this.weight = weight;
        return this;
    }

    public int getFat() {
        return fat;
    }

    public AdminAnnotation setFat(int  fat) {
        this.fat = fat;
        return this;
    }

    public int getHeight() {
        return height;
    }

    public AdminAnnotation setHeight(int height) {
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
