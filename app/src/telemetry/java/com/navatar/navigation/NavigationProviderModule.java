package com.navatar.navigation;

import com.navatar.navigation.details.TelemetryNavigationProvider;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class NavigationProviderModule {

    @Singleton
    @Binds
    abstract NavigationProvider provideNavigationProvider(TelemetryNavigationProvider navigationProvider);

}
