package me.walten.fastgo.base.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import javax.inject.Inject;

import me.walten.fastgo.base.application.IApp;
import me.walten.fastgo.base.mvp.IPresenter;

/*
 * -----------------------------------------------------------------
 * Copyright by Walten, All rights reserved.
 * -----------------------------------------------------------------
 * desc：
 * -----------------------------------------------------------------
 * 2018/5/24 : Create MVPFragment.java (Walten);
 * -----------------------------------------------------------------
 */
public abstract class MVPFragment<T extends IPresenter> extends SimpleFragment {

    private boolean isInjected;

    @Inject
    protected T mPresenter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        if(!isInjected){
            isInjected = true;
            setupComponent(((IApp)(getActivity().getApplication())).getAppComponent());
        }
        mPresenter.attachView(this);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        if (mPresenter != null) mPresenter.detachView();
        super.onDestroyView();
    }

}