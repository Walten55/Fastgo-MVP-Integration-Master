package me.walten.fastgo.demo.business.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;

import butterknife.BindView;
import me.walten.fastgo.base.activitiy.MVPActivity;
import me.walten.fastgo.demo.R;
import me.walten.fastgo.demo.di.component.DaggerActivityComponent;
import me.walten.fastgo.demo.di.module.ActivityModule;
import me.walten.fastgo.di.component.AppComponent;
import me.walten.fastgo.progress.XProgress;

public class MainActivity extends MVPActivity<MainPresenter> implements MainContract.View {

    @BindView(R.id.tv_msg)
    TextView mMsgTv;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        loadRootFragment(R.id.fl_container,new BannerFragment());
        XProgress.create(this)
                .setStyle(XProgress.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .setCancellable(true).show();

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

        // TODO: 请手动在 ActivityComponent 中添加inject()方法

    }

    @Override
    protected boolean enableImmersive(ImmersionBar immersionBar) {
        immersionBar.statusBarColor(R.color.colorPrimary);
        return true;
    }

    @Override
    public void resultWeather(Object obj) {
        mMsgTv.setText(obj.toString());
    }
}