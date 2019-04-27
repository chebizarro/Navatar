package com.navatar.telemetry;

import com.referencepoint.proto.BuildingOuterClass.Building;
import com.referencepoint.proto.CoordinatesOuterClass.Coordinates;
import com.referencepoint.proto.FloorOuterClass.Floor;
import com.referencepoint.proto.LandmarkOuterClass.Landmark;
import com.referencepoint.proto.NavigableSpaceOuterClass.NavigableSpace;
import com.referencepoint.proto.NavigableSpaceOuterClass.Ring;

public class MapBuilder {
    /**
     * Generates an accessible convex BuildingMap for testing purposes.
     *
     * @return An accessible convex map.
     */
    public static Building generateConvexMap() {
        Building.Builder mapBuilder = Building.newBuilder();
        Coordinates.Builder coordinatesBuilder = Coordinates.newBuilder();
        Floor.Builder floorBuilder = Floor.newBuilder();
        Landmark.Builder landmarkBuilder = Landmark.newBuilder();
        NavigableSpace.Builder navigableBuilder = NavigableSpace.newBuilder();
        floorBuilder.setNumber(0);
        landmarkBuilder.setLocation(coordinatesBuilder.setX(1.0).setY(2.5));
        landmarkBuilder.setType(Landmark.LandmarkType.DOOR);
        landmarkBuilder.setName("208");
        floorBuilder.addLandmarks(landmarkBuilder.build());
        landmarkBuilder.setLocation(coordinatesBuilder.setX(2.5).setY(2.0));
        landmarkBuilder.setType(Landmark.LandmarkType.HALLWAY_INTERSECTION);
        floorBuilder.addLandmarks(landmarkBuilder.build());
        landmarkBuilder.setLocation(coordinatesBuilder.setX(5.0).setY(2.5));
        landmarkBuilder.setType(Landmark.LandmarkType.STAIRS);
        floorBuilder.addLandmarks(landmarkBuilder.build());
        landmarkBuilder.setLocation(coordinatesBuilder.setX(2.5).setY(4.0));
        landmarkBuilder.setType(Landmark.LandmarkType.ELEVATOR);
        floorBuilder.addLandmarks(landmarkBuilder.build());
        navigableBuilder.addOuterBoundary(coordinatesBuilder.setX(1.0).setY(2.0));
        navigableBuilder.addOuterBoundary(coordinatesBuilder.setX(5.0).setY(2.0));
        navigableBuilder.addOuterBoundary(coordinatesBuilder.setX(5.0).setY(4.0));
        navigableBuilder.addOuterBoundary(coordinatesBuilder.setX(1.0).setY(4.0));
        floorBuilder.addNavigableSpaces(navigableBuilder.build());
        mapBuilder.addFloors(floorBuilder.build());
        return mapBuilder.build();
    }


    /**
     * Generates a BuildingMap with two disconnected accessible areas for testing purposes.
     *
     * @return An accessible convex map.
     */
    public static Building generateMapTwoAccessibleAreas() {
        Building.Builder mapBuilder = Building.newBuilder();
        Coordinates.Builder coordinatesBuilder = Coordinates.newBuilder();
        Floor.Builder floorBuilder = Floor.newBuilder();
        Landmark.Builder landmarkBuilder = Landmark.newBuilder();
        NavigableSpace.Builder navigableBuilder = NavigableSpace.newBuilder();
        floorBuilder.setNumber(0);
        landmarkBuilder.setLocation(coordinatesBuilder.setX(0.0).setY(2.5));
        landmarkBuilder.setType(Landmark.LandmarkType.DOOR);
        floorBuilder.addLandmarks(landmarkBuilder.build());
        landmarkBuilder.setLocation(coordinatesBuilder.setX(2.5).setY(0.0));
        landmarkBuilder.setType(Landmark.LandmarkType.HALLWAY_INTERSECTION);
        floorBuilder.addLandmarks(landmarkBuilder.build());
        landmarkBuilder.setLocation(coordinatesBuilder.setX(5.0).setY(2.5));
        landmarkBuilder.setType(Landmark.LandmarkType.STAIRS);
        floorBuilder.addLandmarks(landmarkBuilder.build());
        landmarkBuilder.setLocation(coordinatesBuilder.setX(2.5).setY(4.0));
        landmarkBuilder.setType(Landmark.LandmarkType.ELEVATOR);
        floorBuilder.addLandmarks(landmarkBuilder.build());
        navigableBuilder.addOuterBoundary(coordinatesBuilder.setX(0.0).setY(0.0));
        navigableBuilder.addOuterBoundary(coordinatesBuilder.setX(5.0).setY(0.0));
        navigableBuilder.addOuterBoundary(coordinatesBuilder.setX(5.0).setY(4.0));
        navigableBuilder.addOuterBoundary(coordinatesBuilder.setX(0.0).setY(4.0));
        floorBuilder.addNavigableSpaces(navigableBuilder.build());
        navigableBuilder.clear();
        navigableBuilder.addOuterBoundary(coordinatesBuilder.setX(6.0).setY(0.0));
        navigableBuilder.addOuterBoundary(coordinatesBuilder.setX(10.0).setY(0.0));
        navigableBuilder.addOuterBoundary(coordinatesBuilder.setX(10.0).setY(6.0));
        navigableBuilder.addOuterBoundary(coordinatesBuilder.setX(6.0).setY(6.0));
        floorBuilder.addNavigableSpaces(navigableBuilder.build());
        mapBuilder.addFloors(floorBuilder.build());
        return mapBuilder.build();
    }

    /**
     * Generates an accessible concave BuildingMap for testing purposes.
     *
     * @return An accessible convex map.
     */
    public static Building generateConcaveMap() {
        Building.Builder mapBuilder = Building.newBuilder();
        Coordinates.Builder coordinatesBuilder = Coordinates.newBuilder();
        Floor.Builder floorBuilder = Floor.newBuilder();
        Landmark.Builder landmarkBuilder = Landmark.newBuilder();
        NavigableSpace.Builder navigableBuilder = NavigableSpace.newBuilder();
        floorBuilder.setNumber(0);
        landmarkBuilder.setLocation(coordinatesBuilder.setX(0.0).setY(2.5));
        landmarkBuilder.setType(Landmark.LandmarkType.DOOR);
        floorBuilder.addLandmarks(landmarkBuilder.build());
        landmarkBuilder.setLocation(coordinatesBuilder.setX(4.0).setY(7.0));
        landmarkBuilder.setType(Landmark.LandmarkType.DOOR);
        floorBuilder.addLandmarks(landmarkBuilder.build());
        landmarkBuilder.setLocation(coordinatesBuilder.setX(2.5).setY(0.0));
        landmarkBuilder.setType(Landmark.LandmarkType.HALLWAY_INTERSECTION);
        floorBuilder.addLandmarks(landmarkBuilder.build());
        landmarkBuilder.setLocation(coordinatesBuilder.setX(5.0).setY(2.5));
        landmarkBuilder.setType(Landmark.LandmarkType.STAIRS);
        floorBuilder.addLandmarks(landmarkBuilder.build());
        landmarkBuilder.setLocation(coordinatesBuilder.setX(2.5).setY(4.0));
        landmarkBuilder.setType(Landmark.LandmarkType.ELEVATOR);
        floorBuilder.addLandmarks(landmarkBuilder.build());
        navigableBuilder
            .addOuterBoundary(coordinatesBuilder.setX(0.0).setY(0.0))
            .addOuterBoundary(coordinatesBuilder.setX(8.0).setY(0.0))
            .addOuterBoundary(coordinatesBuilder.setX(8.0).setY(2.0))
            .addOuterBoundary(coordinatesBuilder.setX(5.0).setY(2.0))
            .addOuterBoundary(coordinatesBuilder.setX(5.0).setY(6.0))
            .addOuterBoundary(coordinatesBuilder.setX(8.0).setY(6.0))
            .addOuterBoundary(coordinatesBuilder.setX(8.0).setY(7.0))
            .addOuterBoundary(coordinatesBuilder.setX(0.0).setY(7.0));
        floorBuilder.addNavigableSpaces(navigableBuilder.build());
        mapBuilder.addFloors(floorBuilder.build());
        return mapBuilder.build();
    }

    /**
     * Generates an accessible BuildingMap with inaccessible rings in it for testing purposes.
     *
     * @return An accessible convex map.
     */
    public static Building generateMapWithRings() {
        Building.Builder mapBuilder = Building.newBuilder();
        Coordinates.Builder coordinatesBuilder = Coordinates.newBuilder();
        Floor.Builder floorBuilder = Floor.newBuilder();
        Landmark.Builder landmarkBuilder = Landmark.newBuilder();
        NavigableSpace.Builder navigableBuilder = NavigableSpace.newBuilder();
        floorBuilder.setNumber(0);
        landmarkBuilder.setLocation(coordinatesBuilder.setX(0.0).setY(2.5));
        landmarkBuilder.setType(Landmark.LandmarkType.DOOR);
        floorBuilder.addLandmarks(landmarkBuilder.build());
        landmarkBuilder.setLocation(coordinatesBuilder.setX(4.0).setY(7.0));
        landmarkBuilder.setType(Landmark.LandmarkType.DOOR);
        floorBuilder.addLandmarks(landmarkBuilder.build());
        landmarkBuilder.setLocation(coordinatesBuilder.setX(2.5).setY(0.0));
        landmarkBuilder.setType(Landmark.LandmarkType.HALLWAY_INTERSECTION);
        floorBuilder.addLandmarks(landmarkBuilder.build());
        landmarkBuilder.setLocation(coordinatesBuilder.setX(5.0).setY(2.5));
        landmarkBuilder.setType(Landmark.LandmarkType.STAIRS);
        floorBuilder.addLandmarks(landmarkBuilder.build());
        landmarkBuilder.setLocation(coordinatesBuilder.setX(2.5).setY(4.0));
        landmarkBuilder.setType(Landmark.LandmarkType.ELEVATOR);
        floorBuilder.addLandmarks(landmarkBuilder.build());
        navigableBuilder
            .addOuterBoundary(coordinatesBuilder.setX(0.0).setY(0.0))
            .addOuterBoundary(coordinatesBuilder.setX(18.0).setY(0.0))
            .addOuterBoundary(coordinatesBuilder.setX(18.0).setY(15.0))
            .addOuterBoundary(coordinatesBuilder.setX(0.0).setY(15.0));
        Ring.Builder ringBuilder = Ring.newBuilder();
        ringBuilder
            .addPolygon(coordinatesBuilder.setX(1.0).setY(1.0))
            .addPolygon(coordinatesBuilder.setX(6.0).setY(1.0))
            .addPolygon(coordinatesBuilder.setX(6.0).setY(6.0))
            .addPolygon(coordinatesBuilder.setX(1.0).setY(6.0));
        navigableBuilder.addRings(ringBuilder.build());
        ringBuilder.clear();
        ringBuilder
            .addPolygon(coordinatesBuilder.setX(11.0).setY(8.0))
            .addPolygon(coordinatesBuilder.setX(16.0).setY(8.0))
            .addPolygon(coordinatesBuilder.setX(16.0).setY(13.0))
            .addPolygon(coordinatesBuilder.setX(11.0).setY(13.0));
        navigableBuilder.addRings(ringBuilder.build());
        floorBuilder.addNavigableSpaces(navigableBuilder.build());
        mapBuilder.addFloors(floorBuilder.build());
        return mapBuilder.build();
    }
}
