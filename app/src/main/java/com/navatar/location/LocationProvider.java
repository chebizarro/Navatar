package com.navatar.location;

import android.support.annotation.NonNull;

import io.reactivex.Observable;
import com.navatar.location.model.Location;

/**
 * @author Chris Daley
 */
public interface LocationProvider {

    @NonNull
    Observable<Location> getLocationUpdates();

}
