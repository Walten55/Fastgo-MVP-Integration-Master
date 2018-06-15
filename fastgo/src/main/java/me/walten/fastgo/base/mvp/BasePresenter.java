package me.walten.fastgo.base.mvp;

/*
 * -----------------------------------------------------------------
 * Copyright by 2018 Walten, All rights reserved.
 * -----------------------------------------------------------------
 * desc:
 * -----------------------------------------------------------------
 * 2018/5/25 : Create BasePresenter.java (Walten);
 * -----------------------------------------------------------------
 */
public class BasePresenter<T extends BaseView> implements IPresenter<T> {

    protected T mView;

    @Override
    public void attachView(T view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        this.mView = null;
    }
}
