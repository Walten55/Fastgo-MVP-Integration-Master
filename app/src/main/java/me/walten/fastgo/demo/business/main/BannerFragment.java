package me.walten.fastgo.demo.business.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import me.walten.fastgo.base.fragment.MVPFragment;
import me.walten.fastgo.demo.R;
import me.walten.fastgo.demo.di.component.DaggerFragmentComponent;
import me.walten.fastgo.demo.di.module.FragmentModule;
import me.walten.fastgo.di.component.AppComponent;


public class BannerFragment extends MVPFragment<BannerPresenter> implements BannerContract.View {


    public BannerFragment() {
        // Required empty public constructor
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_banner;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void setupComponent(@NonNull AppComponent appComponent) {
        DaggerFragmentComponent.builder()
                .appComponent(appComponent)
                .fragmentModule(new FragmentModule(this))
                .build()
                .inject(this);

        // TODO: 请手动在 FragmentComponent 中添加inject()方法
    }
}

