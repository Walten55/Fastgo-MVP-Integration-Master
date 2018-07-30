package me.walten.fastgo.demo.business.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.gyf.barlibrary.ImmersionBar;

import me.walten.fastgo.base.activitiy.MVPActivity;
import me.walten.fastgo.demo.R;
import me.walten.fastgo.demo.di.component.DaggerActivityComponent;
import me.walten.fastgo.demo.di.module.ActivityModule;
import me.walten.fastgo.di.component.AppComponent;

public class MainActivity extends MVPActivity<MainPresenter> implements MainContract.View {



    @Override
    public int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mPresenter.getWeather("厦门");
    }

    @Override
    public void setupComponent(@NonNull AppComponent appComponent) {
        DaggerActivityComponent.builder()
                .appComponent(appComponent)
                .activityModule(new ActivityModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected boolean enableImmersive(ImmersionBar immersionBar) {
        immersionBar.statusBarColor(R.color.colorPrimary);
        return true;
    }

    @Override
    public void resultWeather(Object obj) {
    }
}