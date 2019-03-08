package com.navatar.routes.details;

import com.navatar.maps.Route;
import com.navatar.pathplanning.AStar;
import com.navatar.pathplanning.Path;
import com.navatar.pathplanning.PathFinder;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

/**
 * @author Chris Daley
 */
@Singleton
public class AStarPathFinder implements PathFinder {

    private AStar mPathFinder = new AStar();

    @Inject
    public AStarPathFinder() {}

    @Override
    public Single<Path> findPath(Route route) {
        return mPathFinder.findPath(route);
    }
}
