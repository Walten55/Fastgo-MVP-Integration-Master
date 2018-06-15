package me.walten.fastgo.di.module;


import android.app.Application;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.walten.fastgo.integration.IRepositoryManager;
import me.walten.fastgo.integration.RepositoryManager;
import me.walten.fastgo.integration.lifecycle.ActivityLifecycle;
import me.walten.fastgo.integration.lifecycle.ActivityLifecycleForRxLifecycle;

/*
 * -----------------------------------------------------------------
 * Copyright by Walten, All rights reserved.
 * -----------------------------------------------------------------
 * desc：
 * -----------------------------------------------------------------
 * 2018/5/24 : Create AppModule.java (Walten);
 * -----------------------------------------------------------------
 */
@Module
public class AppModule {
    private final Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return application;
    }

    @Provides
    @Singleton
    @Named("ActivityLifecycle")
    Application.ActivityLifecycleCallbacks bindActivityLifecycle(ActivityLifecycle activityLifecycle){
        return activityLifecycle;
    }

    @Provides
    @Singleton
    @Named("ActivityLifecycleForRxLifecycle")
    Application.ActivityLifecycleCallbacks bindActivityLifecycleForRxLifecycle(ActivityLifecycleForRxLifecycle activityLifecycleForRxLifecycle){
        return activityLifecycleForRxLifecycle;
    }

    @Provides
    @Singleton
    IRepositoryManager provideRepositoryManager(RepositoryManager repositoryManager){
        return repositoryManager;
    }

}
