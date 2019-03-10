package com.navatar.navigation;

import com.navatar.navigation.details.AndroidNavigationProvider;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class NavigationProviderModule {

    @Singleton
    @Binds
    abstract NavigationProvider provideNavigationProvider(AndroidNavigationProvider navigationProvider);

}
