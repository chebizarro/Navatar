package com.navatar.telemetry.rl.algo;

import com.navatar.telemetry.GridMatrix;
import com.navatar.telemetry.rl.algo.settings.BasicSettings;
import com.navatar.telemetry.rl.state.Action;
import com.navatar.telemetry.rl.state.GridState;
import com.navatar.telemetry.rl.state.StateType;

public class QLearning extends Algorithm {

    public QLearning(GridMatrix matrix, BasicSettings settings) {
        super(matrix, settings);
    }

    @Override
    public boolean run() {

        GridMatrix oldMatrix = matrix.copy();

        GridState currentState = matrix.getStartState();

        while (currentState.getType() != StateType.GOAL) {
            Action chosenAction = getEpsilonGreedyAction(currentState);
            chosenAction.setQvalue(calculateNewQvalue(chosenAction.getNextState(), chosenAction));
            currentState = chosenAction.getNextState();
        }

        return convergence(oldMatrix, matrix);
    }

    @Override
    public void runAll() {
        GridMatrix oldMatrix;

        do {
            oldMatrix = matrix.copy();

            GridState currentState = matrix.getStartState();

            while (currentState.getType() != StateType.GOAL) {

                Action chosenAction = getEpsilonGreedyAction(currentState);

                chosenAction.setQvalue(calculateNewQvalue(chosenAction.getNextState(), chosenAction));

                currentState = chosenAction.getNextState();
            }
        }

        while (!convergence(oldMatrix, matrix));
    }


    public double calculateNewQvalue(GridState from,  Action a) {

        Action bestNextAction = getGreedyAction(from);

        double newQ =  a.getQvalue() + settings.getLearningRate() * (a.getReward() + settings.getDiscountFactor() * bestNextAction.getQvalue() - a.getQvalue());
        output = "Q([" + from.getX() + ", " + from.getY() + "], " + a + ") \u27F5 " + a.getQvalue() + " + " + settings.getLearningRate() + "\u005B" + a.getReward() + " + " + settings.getDiscountFactor() +
                " * " + bestNextAction.getQvalue() + " - " + a.getQvalue() + "\u005D = " + newQ ;

        return newQ;
    }

}
