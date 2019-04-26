package com.navatar.telemetry.rl.algo;

import java.util.Random;

public abstract class Algorithm {

    protected Random r = new Random();


    public abstract String toString();

    public abstract boolean run();


}
