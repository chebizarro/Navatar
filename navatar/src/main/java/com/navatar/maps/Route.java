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
        mPath = mBuilding.getRoute(mFromLandmark, mToLandmark);
    }


    public Landmark getFromLandmark() {
        return mFromLandmark;
    }

    public Landmark getToLandmark() {
        return mToLandmark;
    }

    public void setToLandmark(Landmark landmark) {
        mToLandmark = landmark;
        mPath = mBuilding.getRoute(mFromLandmark, mToLandmark);
    }

    public void setPath(Path path) {
        mPath = path;
    }

    public Path getPath() {
        return mPath;
    }

}
