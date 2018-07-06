package me.walten.fastgo.demo.business.hello;

import me.walten.fastgo.base.mvp.BasePresenter;
import me.walten.fastgo.base.mvp.BaseView;

public interface HelloContract {

    interface View extends BaseView {

    }

    abstract class Presenter extends BasePresenter<View> {

    }
}