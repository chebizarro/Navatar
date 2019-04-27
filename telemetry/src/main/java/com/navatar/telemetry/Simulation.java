package com.navatar.telemetry;

import com.navatar.telemetry.rl.algo.Algorithm;

import java.util.List;

public class Simulation {

    private Environment environment;

    private Algorithm algorithm;

    private List<Agent> agents;


    public Simulation(Environment environment, Algorithm algorithm, List<Agent> agents) {
        this.environment = environment;
        this.algorithm = algorithm;
        this.agents = agents;
    }

    public void run() {
        algorithm.run();
    }

    public void runAll() {
        algorithm.runAll();
    }

}
