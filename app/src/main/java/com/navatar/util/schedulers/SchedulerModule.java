package com.navatar.util.schedulers;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Binds;
import dagger.Provides;

/**
 * @author Chris Daley
 */
@Module
public abstract class SchedulerModule {

     @Singleton
     @Provides
     static BaseSchedulerProvider provideSchedulerProvider() {
          return new ImmediateSchedulerProvider();
     }

}
