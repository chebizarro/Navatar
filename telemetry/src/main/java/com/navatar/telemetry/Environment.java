package com.navatar.telemetry;

import com.navatar.maps.Map;

public class Environment {

    Map map;

    public Environment(Map map) {
        this.map = map;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }
}
