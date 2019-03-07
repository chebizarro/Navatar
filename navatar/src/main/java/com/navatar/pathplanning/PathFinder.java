package com.navatar.pathplanning;

import com.navatar.maps.Route;
import io.reactivex.Single;

public interface PathFinder {

    Single<Path> findPath(Route route);

}
