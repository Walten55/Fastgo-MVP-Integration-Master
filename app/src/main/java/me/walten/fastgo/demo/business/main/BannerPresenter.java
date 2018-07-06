package me.walten.fastgo.demo.business.main;

import javax.inject.Inject;

import me.walten.fastgo.demo.model.APPModel;
import me.walten.fastgo.di.scope.FragmentScope;

@FragmentScope
public class BannerPresenter extends BannerContract.Presenter {

    BannerContract.View mView;

    @Inject
    APPModel mModel;

    @Inject
    public BannerPresenter() {
    }

    @Override
    public void attachView(BannerContract.View view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
        mModel.destroy();
    }
}