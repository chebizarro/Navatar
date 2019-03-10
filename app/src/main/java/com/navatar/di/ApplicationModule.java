package com.navatar.di;

import android.app.Application;
import android.content.Context;

import com.navatar.common.SensorDataProvider;
import com.navatar.common.TextToSpeechProvider;
import com.navatar.common.details.AndroidSensorProvider;
import com.navatar.common.details.AndroidTTSProvider;
import com.navatar.data.source.LandmarkProvider;
import com.navatar.location.GeofencingProvider;
import com.navatar.location.LocationProvider;
import com.navatar.location.details.AndroidLocationProvider;
import com.navatar.location.details.QRCodeScanner;
import com.navatar.navigation.NavigationProvider;
import com.navatar.navigation.details.AndroidNavigationProvider;
import com.navatar.pathplanning.AStar;
import com.navatar.pathplanning.PathFinder;
import com.navatar.routes.details.AStarPathFinder;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.ElementsIntoSet;

/**
 * This is a Dagger module. We use this to bind our Application class as a Context in the AppComponent
 * By using Dagger Android we do not need to pass our Application instance to any module,
 * we simply need to expose our Application as Context.
 * One of the advantages of Dagger.Android is that your Application & Activities are provided
 * into your graph for you.
 * {@link AppComponent}.
 *
 * @author Chris Daley
 */
@Module
public abstract class ApplicationModule {
    //expose Application as an injectable context
    @Binds
    abstract Context bindContext(Application application);

    @Binds
    abstract LocationProvider provideLocationProvider(AndroidLocationProvider locationProvider);

    @Binds
    abstract GeofencingProvider provideGeofencingProvider(AndroidLocationProvider geofencingProvider);

    @Binds
    abstract TextToSpeechProvider provideTextToSpeechProvider(AndroidTTSProvider ttsProvider);

    @Binds
    abstract SensorDataProvider provideSensorDataProvider(AndroidSensorProvider sensorProvider);

    @Binds
    abstract LandmarkProvider provideLandmarkProvider(QRCodeScanner qrs);

    @Binds
    abstract PathFinder providePathFinder(AStarPathFinder aStar);

    //@Binds
    //abstract NavigationProvider provideNavigationProvider(AndroidNavigationProvider androidNavigationProvider);

    @Provides
    @ElementsIntoSet
    static Set<LocationProvider> provideLocationProviders(AndroidLocationProvider alp, QRCodeScanner qrs) {
        return new HashSet<>(Arrays.asList(alp, qrs));
    }

}

