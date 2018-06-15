package me.walten.fastgo.demo.business.main;

import me.walten.fastgo.base.mvp.BasePresenter;
import me.walten.fastgo.base.mvp.BaseView;

public interface MainContract {

    interface View extends BaseView {
        void resultWeather(Object obj);
    }

    abstract class Presenter extends BasePresenter<View> {
        abstract void getWeather(String city);
    }
}