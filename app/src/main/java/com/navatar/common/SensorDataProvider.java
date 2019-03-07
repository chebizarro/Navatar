package com.navatar.common;

import io.reactivex.Flowable;

/**
 * @author Chris Daley
 */
public interface SensorDataProvider {

    Flowable<SensorData> onSensorChanged();

}
