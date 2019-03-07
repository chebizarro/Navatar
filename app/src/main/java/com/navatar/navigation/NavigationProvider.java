package com.navatar.navigation;

import com.navatar.location.model.Location;

import io.reactivex.Observable;

/**
 * @author Chris Daley
 */
public interface NavigationProvider {

    Observable<Location> navigate();

}
