package me.walten.fastgo.demo.application;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.multidex.MultiDex;

import me.walten.fastgo.delegate.IAppDelegate;

public class AppDelegateImpl implements IAppDelegate {
    @Override
    public void attachBaseContext(@NonNull Context context) {
        MultiDex.install(context);
    }

    @Override
    public void onCreate(@NonNull Application application) {

    }

    @Override
    public void onTerminate(@NonNull Application application) {

    }
}