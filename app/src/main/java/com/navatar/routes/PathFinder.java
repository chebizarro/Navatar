package com.navatar.routes;

import com.navatar.maps.Landmark;
import com.navatar.maps.Map;
import com.navatar.maps.Path;

public interface PathFinder {

    Path findPath(Map map, Landmark start, Landmark goal);

}
