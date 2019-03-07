package com.navatar.maps;

import com.navatar.maps.particles.ParticleState;

import java.util.ArrayList;
import java.util.List;

public class Map {

    private final String mId;

    private final String mName;

    private final List<Building> mBuildings;


    public Map(String id, String name, List<Building> buildings) {
        this(id, name);
        mBuildings.addAll(buildings);
    }

    public Map(String id, String name) {
        mId = id;
        mName = name;
        mBuildings = new ArrayList<>();
    }

    public String getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public List<Building> getBuildings() {
        return mBuildings;
    }

    public void addBuilding(Building building) {
        building.setMap(this);
        mBuildings.add(building);
    }

    public Building getBuilding(String buildingId) {
        for (Building building : mBuildings) {
            if (building.getName().equals(buildingId)) {
                return building;
            }
        }
        return  null;
    }

    public List<Landmark> getLandmarks(ParticleState state) {



        return new ArrayList<>();
    }

    @Override
    public String toString() {
        return mName;
    }

}
