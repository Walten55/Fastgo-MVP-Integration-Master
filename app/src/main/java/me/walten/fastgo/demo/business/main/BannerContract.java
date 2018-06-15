package me.walten.fastgo.demo.business.main;

import me.walten.fastgo.base.mvp.BasePresenter;
import me.walten.fastgo.base.mvp.BaseView;

public interface BannerContract {

    interface View extends BaseView {

    }

    abstract class Presenter extends BasePresenter<View> {

    }
}