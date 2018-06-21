package me.walten.fastgo.demo.business.main;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;
import me.walten.fastgo.demo.model.APPModel;
import me.walten.fastgo.di.scope.ActivityScope;

@ActivityScope
public class MainPresenter extends MainContract.Presenter {

    MainContract.View mView;

    @Inject
    APPModel mModel;

    @Inject
    public MainPresenter() {
    }

    @Override
    public void attachView(MainContract.View view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
        mModel.destory();
    }

    @Override
    void getWeather(String city) {
        mModel.getRemoteModel()
                .getWeather(city, new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                mView.resultWeather(o);
            }
        });
    }
}