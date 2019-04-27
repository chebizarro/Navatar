package com.navatar.telemetry.rl.algo.settings;

public class BasicSettings {

    protected double learningRate;
    protected double discountFactor;
    protected double lambda;
    protected double epsilon = 0.1;
    // convergence criteria
    protected double delta = 0.01;


    public BasicSettings() {
        learningRate = 0.1;
        discountFactor = 0.9;
        lambda = 0.8;
    }

    public double getEpsilon() {
        return epsilon;
    }

    public double getDelta() {
        return delta;
    }

    public double getLearningRate() {
        return learningRate;
    }

    public void setLearningRate(double d) {
        learningRate=d;
    }

    public double getLambda() {
        return lambda;
    }

    public void setLambda(double d) {
        lambda=d;
    }

    public double getDiscountFactor() {
        return discountFactor;
    }

    public void setDiscountFactor(double i) {
        discountFactor = i;
    }

    public String toString() {
        return "learning rate: "+learningRate + " discount factor: " + discountFactor;
    }

    public void printSettings() {

        System.out.println("Learning rate:" + learningRate);
        System.out.println("Discount factor: " + discountFactor);
    }

}
