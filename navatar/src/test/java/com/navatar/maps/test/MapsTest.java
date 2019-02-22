package com.navatar.maps.test;

import junit.framework.TestCase;

import com.navatar.maps.Building;
import com.navatar.maps.particles.ParticleState;
import com.navatar.protobufs.BuildingMapProto.BuildingMap;


public class MapsTest extends TestCase {

    public void testGetRoomLocation () {

        BuildingMap instance = BuildingMap.getDefaultInstance();
        Building wrapper = new Building(instance);

        ParticleState state = wrapper.getRoomLocation("Test");

        assertNull(state);
    }
}