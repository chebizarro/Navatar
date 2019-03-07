package com.navatar.data.source;

import com.navatar.maps.Landmark;

import io.reactivex.Flowable;

/**
 * @author Chris Daley
 */
public interface LandmarkProvider {

    Flowable<Landmark> getLandmarks();

}
