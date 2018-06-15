package me.walten.fastgo.base.mvp;

public interface IPresenter<T extends BaseView>{

    void attachView(T view);

    void detachView();
}
