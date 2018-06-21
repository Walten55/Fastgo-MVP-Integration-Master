package me.walten.fastgo.base.application;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.blankj.utilcode.util.Utils;

import java.util.List;

import me.walten.fastgo.common.Fastgo;
import me.walten.fastgo.delegate.AppDelegateImpl;
import me.walten.fastgo.delegate.IAppDelegate;
import me.walten.fastgo.di.component.AppComponent;
import me.walten.fastgo.utils.PreconditionsUtil;

/*
 * -----------------------------------------------------------------
 * Copyright by Walten, All rights reserved.
 * -----------------------------------------------------------------
 * desc：
 * -----------------------------------------------------------------
 * 2018/5/24 : Create BaseApplication.java (Walten);
 * -----------------------------------------------------------------
 */
public class BaseApplication extends Application implements IApp {

    private IAppDelegate mAppDelegate;

    /**
     * 是否需要初始化
     *
     * @return
     */
    public boolean shouldInit() {
        ActivityManager am = ((ActivityManager) getSystemService(Context.ACTIVITY_SERVICE));
        List<ActivityManager.RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
        String mainProcessName = getPackageName();
        int myPid = android.os.Process.myPid();
        for (ActivityManager.RunningAppProcessInfo info : processInfos) {
            if (info.pid == myPid && mainProcessName.equals(info.processName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param base
     */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        if (mAppDelegate == null)
            this.mAppDelegate = new AppDelegateImpl(base);
        this.mAppDelegate.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (mAppDelegate != null)
            this.mAppDelegate.onCreate(this);

        if (!shouldInit())
            return;

        //ARouter.openLog();
        //ARouter.openDebug();
        //ARouter.init(this);

        Utils.init(this);

        Fastgo.init(this);
        Fastgo.printLog(true);
    }

    /**
     * 在模拟环境中程序终止时会被调用
     */
    @Override
    public void onTerminate() {
        super.onTerminate();
        if (mAppDelegate != null)
            this.mAppDelegate.onTerminate(this);
        Fastgo.release();
    }

    @NonNull
    @Override
    public AppComponent getAppComponent() {
        PreconditionsUtil.checkNotNull(mAppDelegate, "%s cannot be null", AppDelegateImpl.class.getName());
        PreconditionsUtil.checkState(mAppDelegate instanceof IApp, "%s must be implements %s", AppDelegateImpl.class.getName(), IApp.class.getName());
        return ((IApp) mAppDelegate).getAppComponent();
    }
}
