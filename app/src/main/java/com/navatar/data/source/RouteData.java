package com.navatar.data.source;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.navatar.maps.Route;
import com.navatar.maps.Building;
import com.navatar.maps.Landmark;
import com.navatar.maps.Map;

import java.util.UUID;

/**
 * Immutable model class for a Route Record
 * @author Chris Daley
 */
@Entity(tableName = "routes")
public final class RouteData {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "entryid")
    private final String mId;

    @NonNull
    @ColumnInfo(name = "time")
    private final String mTime;

    @NonNull
    @ColumnInfo(name = "mapid")
    private final String mMapId;

    @NonNull
    @ColumnInfo(name = "buildingid")
    private String mBuildingId;

    @NonNull
    @ColumnInfo(name = "startid")
    private String mStartId;

    @NonNull
    @ColumnInfo(name = "endid")
    private String mEndId;

    @Ignore
    public RouteData(@NonNull String time, @NonNull String mapId, @NonNull String buildingId, @NonNull String startId, @NonNull String endId) {
        this(UUID.randomUUID().toString(), time, mapId, buildingId, startId, endId);
    }

    public RouteData(@NonNull String id, @NonNull String time, @NonNull String mapId, @NonNull String buildingId, @NonNull String startId, @NonNull String endId) {
        mId = id;
        mTime = time;
        mMapId = mapId;
        mBuildingId = buildingId;
        mStartId = startId;
        mEndId = endId;
    }

    @NonNull
    public String getId() { return mId; }

    @NonNull
    public String getTime() { return mTime; }

    @NonNull
    public String getMapId() { return mMapId; }

    @NonNull
    public String getBuildingId() { return mBuildingId; }

    public void setBuildingId(@NonNull String buildingId) { mBuildingId = buildingId; }

    @NonNull
    public String getStartId() { return mStartId; }

    public void setStartId(@NonNull String startId) { mStartId = startId; }

    @NonNull
    public String getEndId() { return mEndId; }

    public void setEndId(@NonNull String endId) { mEndId = endId; }

    @Override
    public String toString() {
        return mStartId + ":" + mEndId;
    }

    public Route getRoute(Map map) {
        Route route = new Route(map);
        for(Building building : map.getBuildings()) {
            if (building.getName().equals(mBuildingId)) {
                route.setBuilding(building);
                for(Landmark landmark : building.destinations()) {
                    if (landmark.getName().equals(mStartId))
                        route.setFromLandmark(landmark);
                    else if (landmark.getName().equals(mEndId))
                        route.setToLandmark(landmark);
                    if (route.getFromLandmark() != null && route.getToLandmark() != null)
                        break;
                }
                route.setPath(building.getRoute(route.getFromLandmark(), route.getToLandmark()));
                break;
            }
        }
        return route;
    }

}
