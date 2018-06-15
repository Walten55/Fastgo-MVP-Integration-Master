package me.walten.fastgo.delegate;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import me.walten.fastgo.base.IPage;

/*
 * -----------------------------------------------------------------
 * Copyright by Walten, All rights reserved.
 * -----------------------------------------------------------------
 * desc：
 * -----------------------------------------------------------------
 * 2018/5/24 : Create ActivityDelegateImpl.java (Walten);
 * -----------------------------------------------------------------
 */
public class ActivityDelegateImpl implements IActivityDelegate {
    private IPage page;

    public ActivityDelegateImpl(@NonNull Activity activity) {
        this.page = (IPage) activity;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {

    }

    @Override
    public void onDestroy() {

    }
}
