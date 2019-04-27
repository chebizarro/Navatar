package com.navatar.telemetry.rl.algo;

import com.navatar.telemetry.GridMatrix;
import com.navatar.telemetry.rl.algo.settings.BasicSettings;
import com.navatar.telemetry.rl.state.Action;
import com.navatar.telemetry.rl.state.GridState;
import com.navatar.telemetry.rl.state.StateType;

public class QLambda extends Algorithm {

    public QLambda(GridMatrix matrix, BasicSettings settings) {
        super(matrix, settings);
    }

    @Override
    public boolean run() {

        GridMatrix oldMatrix = matrix.copy();

        GridState currentState = matrix.getStartState();
        Action currentAction = getRandomAction(currentState);

        while (currentState.getType() != StateType.GOAL) {

            Action nextEpsilonGreedyAction = getEpsilonGreedyAction(currentAction.getNextState());

            Action nextGreedyAction = getGreedyAction(currentAction.getNextState(), nextEpsilonGreedyAction);

            double delta = currentAction.getReward() + settings.getDiscountFactor() * nextGreedyAction.getQvalue() - currentAction.getQvalue();

            currentAction.accumulateEligibility();

            for (int i = 0; i < matrix.getRows(); i++) {
                for (int j = 0; j < matrix.getColumns(); j++) {
                    GridState el = matrix.getElement(i, j);
                    for (int l = 0; l < el.getActions().size(); l++) {
                        double q = el.getActions().get(l).getQvalue();
                        double e = el.getActions().get(l).getEligibility();
                        el.getActions().get(l).setQvalue(q + settings.getLearningRate() * delta * e);
                        if (nextEpsilonGreedyAction == nextGreedyAction) {
                            el.getActions().get(l).setEligibility(settings.getDiscountFactor() * settings.getLambda() * e);
                        } else {
                            el.getActions().get(l).resetEligibility();
                        }
                    }
                }
            }
            currentState = currentAction.getNextState();
            currentAction = nextEpsilonGreedyAction;
        }

        return convergence(oldMatrix, matrix);
    }

    @Override
    public void runAll() {

        GridMatrix oldMatrix;

        do {
            oldMatrix = matrix.copy();
            GridState currentState = matrix.getStartState();
            Action currentAction = getRandomAction(currentState);
            //currentState = currentAction.getNextState();

            while (currentState.getType() != StateType.GOAL) {

                Action nextEpsilonGreedyAction = getEpsilonGreedyAction(currentAction.getNextState());

                Action nextGreedyAction = getGreedyAction(currentAction.getNextState(), nextEpsilonGreedyAction);

                double delta = currentAction.getReward() + settings.getDiscountFactor() * nextGreedyAction.getQvalue() - currentAction.getQvalue();

                currentAction.accumulateEligibility();
                //currentAction.replaceEligibility();

                for (int i = 0; i < matrix.getRows(); i++) {
                    for (int j = 0; j < matrix.getColumns(); j++) {
                        GridState el = matrix.getElement(i, j);
                        for (int l = 0; l < el.getActions().size(); l++) {

                            double q = el.getActions().get(l).getQvalue();
                            double e = el.getActions().get(l).getEligibility();

                            el.getActions().get(l).setQvalue(q + settings.getLearningRate() * delta * e);

                            if (nextEpsilonGreedyAction == nextGreedyAction) {
                                el.getActions().get(l).setEligibility(settings.getDiscountFactor() * settings.getLambda() * e);
                            } else {
                                el.getActions().get(l).resetEligibility();
                            }
                        }
                    }
                }

                currentState = currentAction.getNextState();
                currentAction = nextEpsilonGreedyAction;
            }
        } while (!convergence(oldMatrix, matrix));
    }


}
