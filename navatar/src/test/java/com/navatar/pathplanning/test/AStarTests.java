package com.navatar.pathplanning.test;

import com.navatar.maps.Building;
import com.navatar.protobufs.BuildingMapProto;

import org.junit.Test;

public class AStarTests {

    private Building mBuilding;

    public AStarTests() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        BuildingMapProto.BuildingMap map = BuildingMapProto.BuildingMap.parseFrom(classLoader.getResourceAsStream("test.nvm"));
        mBuilding = new Building(map);
    }


    @Test
    public void testFindPath() {

    }
}
