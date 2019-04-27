package com.navatar.telemetry;

import com.navatar.telemetry.rl.state.GridState;
import com.navatar.telemetry.rl.state.StartState;
import com.navatar.telemetry.rl.state.StateType;

public class GridMatrix {

    protected GridState[][] matrix;

    public GridMatrix copy() {
        return new GridMatrix();
    }

    public int getRows() {
        return matrix.length;
    }

    public int getColumns() {
        return matrix[0].length;
    }

    public GridState getElement(int x, int y) {
        if (isInMatrix(x, y)) {
            return matrix[x][y];
        } else {
            System.out.println("(GET) Invalid cell!");
            return null;
        }
    }

    private boolean isInMatrix(int x, int y) {
        return x < getRows() && x > -1 && y < getColumns() && y > -1;
    }

    public StartState getStartState() {
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getColumns(); j++) {
                if (getElement(i, j).getType() == StateType.START) {
                    return (StartState) getElement(i, j);
                }
            }
        }
        System.err.println("No Start State found!");
        return null;
    }


}
