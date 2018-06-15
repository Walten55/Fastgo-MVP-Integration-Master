package me.walten.fastgo.base.application;

import android.support.annotation.NonNull;

import me.walten.fastgo.di.component.AppComponent;
/*
 * -----------------------------------------------------------------
 * Copyright by Walten, All rights reserved.
 * -----------------------------------------------------------------
 * desc：
 * -----------------------------------------------------------------
 * 2018/5/24 : Create IApp.java (Walten);
 * -----------------------------------------------------------------
 */
public interface IApp {
    @NonNull
    AppComponent getAppComponent();
}
