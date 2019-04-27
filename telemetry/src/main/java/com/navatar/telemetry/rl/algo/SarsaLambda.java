package com.navatar.telemetry.rl.algo;

import com.navatar.telemetry.GridMatrix;
import com.navatar.telemetry.rl.state.Action;
import com.navatar.telemetry.rl.state.GridState;
import com.navatar.telemetry.rl.state.StateType;

public class SarsaLambda extends Algorithm {


    @Override
    public boolean run() {

        GridMatrix oldMatrix = matrix.copy();

        GridState currentState = matrix.getStartState();
        Action currentAction = getRandomAction(currentState);

        while (currentState.getType() != StateType.GOAL) {

            Action nextEpsilonGreedyAction = getEpsilonGreedyAction(currentAction.getNextState());
            double delta = currentAction.getReward() + settings.getDiscountFactor() * nextEpsilonGreedyAction.getQvalue() - currentAction.getQvalue();

            currentAction.accumulateEligibility();

            for (int i = 0; i < matrix.getRows(); i++) {
                for (int j = 0; j < matrix.getColumns(); j++) {
                    GridState el = matrix.getElement(i, j);
                    for (int l = 0; l < el.getActions().size(); l++) {
                        double q = el.getActions().get(l).getQvalue();
                        double e = el.getActions().get(l).getEligibility();
                        el.getActions().get(l).setQvalue(q + settings.getLearningRate() * delta * e);
                        el.getActions().get(l).setEligibility(settings.getDiscountFactor() * settings.getLambda() * e);
                    }
                }
            }
            currentState = currentAction.getNextState();
            currentAction = nextEpsilonGreedyAction;
        }
        return convergence(oldMatrix, matrix);
    }
}
