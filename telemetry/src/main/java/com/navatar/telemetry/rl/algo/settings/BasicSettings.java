package com.navatar.telemetry.rl.algo.settings;

public class BasicSettings {

    private double learningRate = 0.1;
    private double discountFactor = 0.9;
    private double lambda = 0.8;
    private double epsilon = 0.1;
    // convergence criteria
    private double delta = 0.01;

    public BasicSettings() {  }

    public BasicSettings(double learningRate, double discountFactor, double lambda) {
        this.learningRate = learningRate;
        this.discountFactor = discountFactor;
        this.lambda = lambda;
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
