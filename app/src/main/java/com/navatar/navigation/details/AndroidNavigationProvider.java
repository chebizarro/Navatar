package com.navatar.navigation.details;

import com.navatar.common.SensorDataProvider;
import com.navatar.data.source.LandmarkProvider;
import com.navatar.data.source.RoutesRepository;
import com.navatar.location.GeofencingProvider;
import com.navatar.location.LocationInteractor;
import com.navatar.navigation.NavigationProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class AndroidNavigationProvider implements NavigationProvider {

    private final CompositeDisposable disposables = new CompositeDisposable();

    @Inject
    RoutesRepository mRoutesRepository;

    @Inject
    GeofencingProvider mGeofencingProvider;

    @Inject
    LocationInteractor mLocationInteractor;

    @Inject
    SensorDataProvider mSensorDataProvider;

    @Inject
    LandmarkProvider mLandmarkProvider;

    @Inject
    public AndroidNavigationProvider() {}



}
