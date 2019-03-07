package com.navatar.common.details;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import com.google.android.gms.location.ActivityRecognition;
import com.google.android.gms.location.ActivityTransitionEvent;
import com.google.android.gms.location.ActivityTransitionResult;
import com.google.android.gms.location.DetectedActivity;
import com.google.android.gms.location.ActivityTransition;
import com.google.android.gms.location.ActivityTransitionRequest;
import com.google.android.gms.tasks.Task;
import com.navatar.common.SensorData;
import com.navatar.common.SensorDataProvider;
import com.navatar.sensing.NavatarSensor;
import com.navatar.sensing.NavatarSensorListener;
import com.navatar.sensing.SensingService;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.subjects.PublishSubject;

import static android.content.Context.BIND_AUTO_CREATE;

/**
 * Aggregates sensor data on the Android Platform
 * Implements the {@link SensorDataProvider} interace
 * @author Chris Daley
 */
@Singleton
public final class AndroidSensorProvider extends BroadcastReceiver implements SensorDataProvider, NavatarSensorListener {

    private static final String TAG = AndroidSensorProvider.class.getSimpleName();

    private Context mContext;

    private final PublishSubject<SensorData> mSensorData = PublishSubject.create();

    private SensingService mSensingService;

    private ServiceConnection mSensingConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            SensingService.SensingBinder binder = (SensingService.SensingBinder) service;
            mSensingService = binder.getService();
            mSensingService.registerListener(AndroidSensorProvider.this,
                    new int[] { NavatarSensor.COMPASS, NavatarSensor.PEDOMETER });
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mSensingService = null;
        }
    };

    @Inject
    public AndroidSensorProvider(Context context) {
        mContext = context;
        Intent sensingIntent = new Intent(context, SensingService.class);
        context.startService(sensingIntent);
        context.bindService(sensingIntent, mSensingConnection, BIND_AUTO_CREATE);
        setupActivityTransitions();
    }

    private void setupActivityTransitions() {

        List<ActivityTransition> transitions = new ArrayList<>();

        transitions.add(
                new ActivityTransition.Builder()
                        .setActivityType(DetectedActivity.WALKING)
                        .setActivityTransition(ActivityTransition.ACTIVITY_TRANSITION_ENTER)
                        .build());

        transitions.add(
                new ActivityTransition.Builder()
                        .setActivityType(DetectedActivity.WALKING)
                        .setActivityTransition(ActivityTransition.ACTIVITY_TRANSITION_EXIT)
                        .build());

        transitions.add(
                new ActivityTransition.Builder()
                        .setActivityType(DetectedActivity.STILL)
                        .setActivityTransition(ActivityTransition.ACTIVITY_TRANSITION_ENTER)
                        .build());

        transitions.add(
                new ActivityTransition.Builder()
                        .setActivityType(DetectedActivity.STILL)
                        .setActivityTransition(ActivityTransition.ACTIVITY_TRANSITION_EXIT)
                        .build());

        ActivityTransitionRequest request = new ActivityTransitionRequest(transitions);

        Intent intent = new Intent(mContext, AndroidSensorProvider.class);
        PendingIntent pi = PendingIntent.getBroadcast(mContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Task<Void> task =
            ActivityRecognition.getClient(mContext).requestActivityTransitionUpdates(request,pi);

        task.addOnSuccessListener(
            (v) -> {
                Log.i(TAG, "Activity Transition Request Succesful");
            }
        );

        task.addOnCompleteListener(
            (t) -> {
                Log.i(TAG, "Activity Transition Complete");
            }
        );

        task.addOnFailureListener(
            (e) -> {
                Log.e(TAG, "Activity Transition Request Error:", e);
            }
        );

    }


    @Override
    public Flowable<SensorData> onSensorChanged() {
        return mSensorData.toFlowable(BackpressureStrategy.LATEST);
    }


    @Override
    public void onSensorChanged(float[] values, int sensor, long timestamp) {
        mSensorData.onNext(new SensorData(SensorData.SensorType.values()[sensor], values, timestamp));
    }


    @Override
    public void onReceive(Context context, Intent intent) {
        if (ActivityTransitionResult.hasResult(intent)) {
            ActivityTransitionResult result = ActivityTransitionResult.extractResult(intent);
            if (result != null) {
                for (ActivityTransitionEvent event : result.getTransitionEvents()) {
                    Log.i(TAG, event.toString());
                }
            }
        }
    }
}
