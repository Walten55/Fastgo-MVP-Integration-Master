package me.walten.fastgo.base.mvp;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import me.walten.fastgo.integration.IRepositoryManager;

/**
 * Model基类
 */
public class BaseModel implements IModel{
    protected IRepositoryManager mRepositoryManager;

    public BaseModel(IRepositoryManager repositoryManager){
        mRepositoryManager = repositoryManager;
    }

    protected CompositeDisposable mCompositeDisposable;

    protected void unSubscribe() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }

    protected void addSubscribe(Disposable subscription) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(subscription);
    }

    @Override
    public void destory() {
        unSubscribe();
    }
}
