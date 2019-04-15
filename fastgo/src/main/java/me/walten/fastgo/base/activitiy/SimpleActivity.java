package me.walten.fastgo.base.activitiy;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.WindowManager;

import com.blankj.utilcode.util.KeyboardUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.trello.rxlifecycle2.android.ActivityEvent;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;
import me.walten.fastgo.base.IPage;
import me.walten.fastgo.base.mvp.BaseView;
import me.walten.fastgo.dialog.XTipDialog;
import me.walten.fastgo.integration.lifecycle.ActivityLifecycleable;
import me.walten.fastgo.progress.XProgress;
import me.yokeyword.fragmentation.SupportActivity;

/*
 * -----------------------------------------------------------------
 * Copyright by Walten, All rights reserved.
 * -----------------------------------------------------------------
 * desc：
 * -----------------------------------------------------------------
 * 2018/5/24 : Create SimpleActivity.java (Walten);
 * -----------------------------------------------------------------
 */
public abstract class SimpleActivity extends SupportActivity implements BaseView, IPage ,ActivityLifecycleable {

    private final BehaviorSubject<ActivityEvent> mLifecycleSubject = BehaviorSubject.create();

    protected Activity mContext;

    private Unbinder mUnBinder;

    protected boolean updateOnResume;

    protected ImmersionBar mImmersionBar;

    protected XProgress xProgress;

    private XTipDialog mTipDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        mUnBinder = ButterKnife.bind(this);
        mContext = this;

        //mImmersionBar = ImmersionBar.with(this);
        if (enableImmersive(mImmersionBar)) {
            //利用一个BUG实现沉浸式状态栏
            //setFullScreen();
            //cancelFullScreen();

            mImmersionBar.init();   //所有子类都将继承这些相同的属性
        }

        initView(savedInstanceState);

        initData(savedInstanceState);

    }

    /**
     * 是否允许沉浸式
     *
     * @return
     */
    protected abstract boolean enableImmersive(ImmersionBar immersionBar);

    /**
     * 取消全屏
     */
    protected void cancelFullScreen() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN, WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
    }

    /**
     * 设置全屏
     */
    protected void setFullScreen() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        KeyboardUtils.hideSoftInput(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnBinder.unbind();
        if (mImmersionBar != null)
            mImmersionBar.destroy();
    }

    /**
     * 提示框
     *
     * @param msg
     */
    public void showTipDialog(int opsStatus, String msg) {
        stopWaiting();

        mTipDialog = new XTipDialog.Builder(this)
                .setIconType(opsStatus)
                .setTipWord(msg)
                .create();
        try {
            if (!isFinishing())
                mTipDialog.show();
        } catch (Exception e) {

        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mTipDialog.dismiss();
            }
        }, 1000);
    }

    /**
     * 提示框
     *
     * @param msg
     */
    public void showTipDialog(String msg) {
        showTipDialog(XTipDialog.Builder.ICON_TYPE_INFO, msg);
    }

    /**
     * 加载框
     *
     * @param msg
     */
    public void startWaiting(String msg) {
        stopWaiting();
        mTipDialog = new XTipDialog.Builder(this)
                .setIconType(XTipDialog.Builder.ICON_TYPE_LOADING)
                .setTipWord(msg)
                .create();
        mTipDialog.setCancelable(false);
        mTipDialog.setCanceledOnTouchOutside(false);

        if (!isFinishing())
            mTipDialog.show();
    }

    /**
     * 隐藏加载框
     */
    @Override
    public void stopWaiting() {
        if (mTipDialog != null && mTipDialog.isShowing())
            mTipDialog.dismiss();
    }

    /**
     * 吐司
     *
     * @param opsStatus
     * @param msg
     */
    @Override
    public void showToast(int opsStatus, String msg) {

    }

    /**
     * 吐司
     *
     * @param msg
     */
    @Override
    public void showToast(String msg) {

    }


    @NonNull
    @Override
    public Subject<ActivityEvent> provideLifecycleSubject() {
        return mLifecycleSubject;
    }


    @Override
    public void finishView() {
        finish();
    }
}
