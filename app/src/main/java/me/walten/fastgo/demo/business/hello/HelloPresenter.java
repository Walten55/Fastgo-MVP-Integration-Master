package me.walten.fastgo.demo.business.hello;

import javax.inject.Inject;

import me.walten.fastgo.demo.model.APPModel;
import me.walten.fastgo.di.scope.ActivityScope;

@ActivityScope
public class HelloPresenter extends HelloContract.Presenter {

    HelloContract.View mView;

    @Inject
    APPModel mModel;

    @Inject
    public HelloPresenter() {
    }

    @Override
    public void attachView(HelloContract.View view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
        mModel.destroy();
    }
}