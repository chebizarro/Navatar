package com.navatar.navigation;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;

public class NavigationService extends IntentService {

    private static final String TAG = NavigationService.class.getSimpleName();


    public NavigationService(){
        super("NavigationService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {



    }
}
