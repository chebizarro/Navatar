package com.navatar.telemetry;

import com.navatar.telemetry.rl.algo.Algorithm;

import java.util.List;

public class Simulation {

    private Environment environment;

    private Algorithm algorithm;

    private List<Agent> agents;

    public Simulation(Environment environment, List<Agent> agents) {
        this.environment = environment;
        this.agents = agents;
    }

}
