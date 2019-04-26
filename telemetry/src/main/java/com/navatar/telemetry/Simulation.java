package com.navatar.telemetry;

import java.util.List;

public class Simulation {

    Environment environment;

    List<Agent> agents;

    public Simulation(Environment environment, List<Agent> agents) {
        this.environment = environment;
        this.agents = agents;
    }

}
