package me.walten.fastgo.base.activitiy;

import android.os.Bundle;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import me.walten.fastgo.base.application.IApp;
import me.walten.fastgo.base.mvp.IPresenter;
import me.walten.fastgo.integration.lifecycle.ActivityLifecycleable;

/*
 * -----------------------------------------------------------------
 * Copyright by Walten, All rights reserved.
 * -----------------------------------------------------------------
 * desc：
 * -----------------------------------------------------------------
 * 2018/5/24 : Create MVPActivity.java (Walten);
 * -----------------------------------------------------------------
 */
public abstract class MVPActivity<T extends IPresenter> extends SimpleActivity implements ActivityLifecycleable{

    @Inject
    protected T mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        setupComponent(((IApp)getApplication()).getAppComponent());

        if (mPresenter != null)
            mPresenter.attachView(this);

        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null)
            mPresenter.detachView();
        super.onDestroy();
    }


}