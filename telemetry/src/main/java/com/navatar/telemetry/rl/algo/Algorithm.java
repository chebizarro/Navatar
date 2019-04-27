package com.navatar.telemetry.rl.algo;

import com.navatar.telemetry.GridMatrix;
import com.navatar.telemetry.rl.algo.settings.BasicSettings;
import com.navatar.telemetry.rl.state.Action;
import com.navatar.telemetry.rl.state.GridState;

import java.util.Random;
import java.util.Vector;

public abstract class Algorithm {

    protected GridMatrix matrix;

    protected String output;

    protected Random r = new Random();

    protected BasicSettings settings;

    public Algorithm(GridMatrix matrix, BasicSettings settings) {
        this.matrix = matrix;
        this.settings = settings;
    }

    protected boolean convergence(GridMatrix oldM, GridMatrix newM) {
        for (int i = 0; i < oldM.getRows(); i++) {
            for (int j = 0; j < oldM.getColumns(); j++) {
                GridState oldElement = oldM.getElement(i, j);
                GridState newElement = newM.getElement(i, j);
                for (int l = 0; l < oldElement.getActions().size(); l++) {
                    Vector<Action> oldActions = oldElement.getActions();
                    Vector<Action> newActions = newElement.getActions();
                    if (Math.abs(oldActions.get(l).getQvalue() - newActions.get(l).getQvalue()) > settings.getDelta()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    protected Action getEpsilonGreedyAction(GridState s) {

        if (r.nextDouble() > settings.getEpsilon()) {
            return getGreedyAction(s);
        } else {
            return getRandomAction(s);
        }
    }

    protected Action getGreedyAction(GridState s, Action defaultAction) {
        double maximumQ = defaultAction.getQvalue();
        Action maximumAction = defaultAction;
        for (int i = 0; i < s.getActions().size(); i++) {
            if (s.getActions().get(i).getQvalue() > maximumQ) {
                maximumAction = s.getActions().get(i);
                maximumQ = s.getActions().get(i).getQvalue();
            }
        }
        return maximumAction;
    }


    protected Action getGreedyAction(GridState s) {
        double maximumQ = -1 * Double.MAX_VALUE;
        Action maximumAction = null;
        //System.out.println(s + " " + s.getActions().size());
        for (int i = 0; i < s.getActions().size(); i++) {
            if (s.getActions().get(i).getQvalue() > maximumQ) {
                maximumAction = s.getActions().get(i);
                maximumQ = s.getActions().get(i).getQvalue();
            }
        }
        return maximumAction;
    }


    protected Action getRandomAction(GridState s) {

        int size = s.getActions().size();
        int randomIndex = r.nextInt(size);
        return s.getActions().get(randomIndex);
    }


    public abstract boolean run();

    public abstract void runAll();

}
