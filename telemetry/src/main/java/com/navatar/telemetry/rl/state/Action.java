package com.navatar.telemetry.rl.state;

public class Action {

    private double Qvalue = 0.0;
    private double reward;
    private GridState nextState;
    private Direction direction;
    private double eligibility;

    public Action(GridState nextState, double Q, double reward, Direction direction, double E) {
        Qvalue = Q;
        this.reward = reward;
        this.nextState = nextState;
        this.direction = direction;
        this.eligibility = E;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public double getEligibility() {
        return eligibility;
    }

    public void accumulateEligibility() {
        eligibility++;
    }

    public void replaceEligibility() {
        eligibility=1.0;
    }

    public void resetEligibility() {
        eligibility = 0.0;
    }

    public void setEligibility(double p) {
        eligibility = p;
    }

    public double getQvalue() { return Qvalue; }

    public void resetQValue() {
        Qvalue = 0.0;
    }

    public void setQvalue(double qvalue) { Qvalue = qvalue; }

    public double getReward() {
        return reward;
    }

    public GridState getNextState() {
        return nextState;
    }

}
