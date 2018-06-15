package me.walten.fastgo.delegate;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

/*
 * -----------------------------------------------------------------
 * Copyright by Walten, All rights reserved.
 * -----------------------------------------------------------------
 * desc：
 * -----------------------------------------------------------------
 * 2018/5/24 : Create IAppDelegate.java (Walten);
 * -----------------------------------------------------------------
 */
public interface IAppDelegate {
    void attachBaseContext(@NonNull Context context);

    void onCreate(@NonNull Application application);

    void onTerminate(@NonNull Application application);
}
