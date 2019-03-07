package com.navatar.maps;

import com.navatar.pathplanning.Path;

public final class Route {

    private final Map mMap;

    private Building mBuilding;

    private Landmark mFromLandmark;

    private Landmark mToLandmark;

    private Path mPath;

    public Route(Map map) {
        mMap = map;
    }

    public Building getBuilding() {
        return mBuilding;
    }

    public void setBuilding(Building building) {
        mBuilding = building;
    }

    public void setFromLandmark(Landmark landmark) {
        mFromLandmark = landmark;
    }


    public Landmark getFromLandmark() {
        return mFromLandmark;
    }

    public Landmark getToLandmark() {
        return mToLandmark;
    }

    public void setToLandmark(Landmark landmark) {
        mToLandmark = landmark;
    }

    public void setPath(Path path) {
        mPath = path;
    }

    public Path getPath() {
        return mPath;
    }

    /*
    private void getRoute() {

        if (mFromLandmark == null || mToLandmark == null)
            return;


        AStar pathFinder = new AStar(mBuilding);


        Path path = pathFinder.findPath(mFromLandmark, mToLandmark);



        CoordinatesProto.Coordinates particle = mFromLandmark.getParticles(0);
        ParticleState startState = new ParticleState(0,
            particle.getX(), particle.getY(), mFromLandmark.getFloor());

        particle = mToLandmark.getParticles(0);

        ParticleState endState = new ParticleState(0,
                particle.getX(), particle.getY(), mFromLandmark.getFloor());

        Path path = pathFinder.findPath(startState, mFromLandmark, endState, mToLandmark);
        Direction directionGenerator = new Direction(getProtobufMap());

        if (path != null) {
            path = directionGenerator.generateDirections(path);
        }

        mPath = path;


    }*/

}
