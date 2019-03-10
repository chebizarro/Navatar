package com.navatar.navigation.details;

import android.util.Log;

import com.navatar.common.SensorData;
import com.navatar.common.SensorDataProvider;
import com.navatar.data.source.LandmarkProvider;
import com.navatar.data.source.MapsRepository;
import com.navatar.data.source.RoutesRepository;
import com.navatar.location.GeofencingProvider;
import com.navatar.location.LocationInteractor;
import com.navatar.location.LocationProvider;
import com.navatar.location.model.Location;
import com.navatar.maps.Route;
import com.navatar.maps.particles.ParticleState;
import com.navatar.math.Angles;
import com.navatar.navigation.NavigationProvider;
import com.navatar.particlefilter.ParticleFilter;
import com.navatar.particlefilter.Transition;
import com.navatar.pathplanning.Path;
import com.navatar.pathplanning.PathFinder;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

/**
 * AndroidNavigationProvider implements {@link NavigationProvider}
 *
 * @author Chris Daley
 */
@Singleton
public class AndroidNavigationProvider implements NavigationProvider {

    private static final int METERS_FROM_PATH = 5;
    private static final int COMPASS_COUNTER_MAX = 10;

    private final CompositeDisposable disposables = new CompositeDisposable();

    @Inject
    PathFinder mPathFinder;

    @Inject
    MapsRepository mMapRepository;

    @Inject
    LocationProvider mLocationInteractor;

    @Inject
    SensorDataProvider mSensorDataProvider;

    private final PublishSubject<SensorData> sensorDataPublishSubject = PublishSubject.create();

    private Route mCurrentRoute;

    private ParticleFilter mFilter;

    private int mCompassCounter = 0;
    private double[] mCompassReadingArray;
    private int mCompassAverage = 0;
    private int mOrientation;

    @Inject
    public AndroidNavigationProvider() {}

    private void startNavigation(Route route) {

        mCurrentRoute = route;

        ParticleState startState = mCurrentRoute.getPath().getStep(0).getParticleState();

        mFilter = new ParticleFilter(mMapRepository.getSelectedMap(), startState);

        disposables.add(mFilter.onStateChange()
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::executePathCorrection));

        disposables.add(mLocationInteractor.getLocationUpdates()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                location -> {

                },
                throwable -> {

                }
            )
        );

        disposables.add(mSensorDataProvider.onSensorChanged()
            .share()
            .subscribe(sensorDataPublishSubject::onNext)
        );

        disposables.add(sensorDataPublishSubject
            .filter(s -> s.getSensorType() == SensorData.SensorType.COMPASS)
            .subscribe(this::compassCorrection)
        );

        disposables.add(sensorDataPublishSubject
            .filter(s -> s.getSensorType() == SensorData.SensorType.PEDOMETER)
            .subscribe(this::stepSensorCorrection)
        );
    }

    private void compassCorrection(SensorData data) {

        mOrientation = Angles.discretizeAngle(Angles.compassToScreen(Math.toDegrees(data.getValues()[0])));
        mCompassReadingArray[mCompassCounter++] = mOrientation;

        if (mCompassCounter >= COMPASS_COUNTER_MAX) {
            mCompassAverage = Angles.discretizeAngle(Angles.average(mCompassReadingArray));
            mCompassCounter = 0;
            mFilter.addTransition(new Transition(mCompassAverage, 0, data.getTimeStamp(), 0.0, null, false));
            mFilter.execute();
        }
    }

    private void stepSensorCorrection(SensorData data) {
        mFilter.addTransition(new Transition(mCompassAverage, 1, data.getTimeStamp(), 0.0, null, false));
        mFilter.execute();
    }

    private void executePathCorrection(ParticleState locationEstimate) {
        double distanceFromPath = mCurrentRoute.getPath().distance(locationEstimate);

        if (distanceFromPath > METERS_FROM_PATH) {

            //ParticleState endState = map.getRoomLocation(toRoom.getName());

            disposables.add(mPathFinder.findPath(mCurrentRoute)
                .subscribe(path -> {
                    mCurrentRoute.setPath(path);
                }));

        }
    }

    private void endNavigation() {
        disposables.dispose();
    }

    @Override
    public Observable<Location> navigate() {
        return null;
    }
}
