package me.walten.fastgo.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import me.walten.fastgo.di.component.AppComponent;

/*
 * -----------------------------------------------------------------
 * Copyright by Walten, All rights reserved.
 * -----------------------------------------------------------------
 * desc：
 * -----------------------------------------------------------------
 * 2018/5/24 : Create IPage.java (Walten);
 * -----------------------------------------------------------------
 */
public interface IPage {

    int getLayoutResId();

    void initView(@Nullable Bundle savedInstanceState);

    void initData(@Nullable Bundle savedInstanceState);

    void setupComponent(@NonNull AppComponent appComponent);

}
