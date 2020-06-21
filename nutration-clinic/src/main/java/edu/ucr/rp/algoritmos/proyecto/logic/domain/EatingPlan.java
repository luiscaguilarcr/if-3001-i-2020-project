package edu.ucr.rp.algoritmos.proyecto.logic.domain;

public class EatingPlan {
    private String carbohydrates;
    private String flours;
    private String fruits;
    private int iD;

    public String getCarbohydrates() {
        return carbohydrates;
    }

    public EatingPlan setCarbohydrates(String carbohydrates) {
        this.carbohydrates = carbohydrates;
        return this;
    }

    public String getFlours() {
        return flours;
    }

    public EatingPlan setFlours(String flours) {
        this.flours = flours;
        return this;
    }

    public String getFruits() {
        return fruits;
    }

    public EatingPlan setFruits(String fruits) {
        this.fruits = fruits;
        return this;
    }

    public int getID() {
        return iD;
    }

    public EatingPlan setID(int iD) {
        this.iD = iD;
        return this;
    }
}
