package com.navatar.navigation.details;

import com.navatar.location.model.Location;
import com.navatar.navigation.NavigationProvider;
import com.navatar.telemetry.Telemetry;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class TelemetryNavigationProvider implements NavigationProvider {

    @Inject
    AndroidNavigationProvider mAndroidNavigationProvider;

    private Telemetry mTelemetry;

    @Inject
    public TelemetryNavigationProvider() {}

    @Override
    public Observable<Location> navigate() {
        return mAndroidNavigationProvider.navigate();
    }

}
