package com.navatar.telemetry.rl.state;

import java.util.Vector;

public abstract class GridState {

    protected boolean isEmptyCell;

    protected boolean isVisitable;

    protected Vector<Action> actions = new Vector<Action>();

    protected StateType type;

    private int x_location;
    private int y_location;

    public Action getPolicy() {
        double highestQ = -1;
        Action highestA = null;

        for (int i = 0; i < actions.size(); i++) {
            if (actions.get(i).getQvalue() > highestQ) {
                highestQ = actions.get(i).getQvalue();
                highestA = actions.get(i);
            }
        }
        return highestA;
    }


    public boolean isEqualActionProbability() {
        if (actions.size() > 0) {
            double Q = actions.get(0).getQvalue();

            for (int i = 1; i < actions.size(); i++) {
                if (actions.get(i).getQvalue() != Q) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public StateType getType() {
        return type;
    }

    public Vector<Action> getActions() {
        return actions;
    }

    public int getX() {
        return x_location;
    }

    public int getY() {
        return y_location;
    }


}
