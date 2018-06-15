package me.walten.fastgo.delegate;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import me.walten.fastgo.base.application.IApp;
import me.walten.fastgo.di.component.AppComponent;
import me.walten.fastgo.di.component.DaggerAppComponent;
import me.walten.fastgo.di.module.AppModule;
import me.walten.fastgo.di.module.GlobalConfigModule;
import me.walten.fastgo.di.module.ThirdPartyModule;
import me.walten.fastgo.integration.ConfigModule;
import me.walten.fastgo.integration.ManifestParser;
import me.walten.fastgo.utils.PreconditionsUtil;
/*
 * -----------------------------------------------------------------
 * Copyright by Walten, All rights reserved.
 * -----------------------------------------------------------------
 * desc：
 * -----------------------------------------------------------------
 * 2018/5/24 : Create AppDelegateImpl.java (Walten);
 * -----------------------------------------------------------------
 */
public class AppDelegateImpl implements IApp, IAppDelegate {
    private Application mApplication;

    private AppComponent mAppComponent;

    @Inject
    @Named("ActivityLifecycle")
    protected Application.ActivityLifecycleCallbacks mActivityLifecycle;

    @Inject
    @Named("ActivityLifecycleForRxLifecycle")
    protected Application.ActivityLifecycleCallbacks mActivityLifecycleForRxLifecycle;

    @Inject
    protected PrettyFormatStrategy formatStrategy;

    private List<ConfigModule> mModules;

    private List<IAppDelegate> mIAppLifecycles = new ArrayList<>();

    private List<Application.ActivityLifecycleCallbacks> mActivityLifecycles = new ArrayList<>();

    public AppDelegateImpl(@NonNull Context context) {

        //用反射, 将 AndroidManifest.xml 中带有 ConfigModule 标签的 class 转成对象集合（List<ConfigModule>）
        this.mModules = new ManifestParser(context).parse();

        //遍历之前获得的集合, 执行每一个 ConfigModule 实现类的某些方法
        for (ConfigModule module : mModules) {

            //将框架外部, 开发者实现的 Application 的生命周期回调 (IAppDelegate) 存入 mIAppLifecycles 集合 (此时还未注册回调)
            module.injectAppLifecycle(context, mIAppLifecycles);

            //将框架外部, 开发者实现的 Activity 的生命周期回调 (ActivityLifecycleCallbacks) 存入 mActivityLifecycles 集合 (此时还未注册回调)
            module.injectActivityLifecycle(context, mActivityLifecycles);

        }
    }

    @Override
    public void attachBaseContext(@NonNull Context base) {
        //遍历 mIAppLifecycles, 执行所有已注册的 IAppDelegate 的 attachBaseContext() 方法 (框架外部, 开发者扩展的逻辑)
        for (IAppDelegate lifecycle : mIAppLifecycles) {
            lifecycle.attachBaseContext(base);
        }
    }

    @Override
    public void onCreate(@NonNull Application application) {
        this.mApplication = application;
        mAppComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(mApplication))
                .globalConfigModule(handlerGlobalConfigModule(GlobalConfigModule.builder()))
                .thirdPartyModule(new ThirdPartyModule())
                .build();
        mAppComponent.inject(this);

        this.mModules = null;

        //注册框架内部已实现的 Activity 生命周期逻辑
        mApplication.registerActivityLifecycleCallbacks(mActivityLifecycle);

        //注册框架内部已实现的 RxLifecycle 逻辑
        mApplication.registerActivityLifecycleCallbacks(mActivityLifecycleForRxLifecycle);

        //注册框架外部, 开发者扩展的 Activity 生命周期逻辑
        //每个 ConfigModule 的实现类可以声明多个 Activity 的生命周期回调
        //也可以有 N 个 ConfigModule 的实现类 (完美支持组件化项目 各个 Module 的各种独特需求)
        for (Application.ActivityLifecycleCallbacks lifecycle : mActivityLifecycles) {
            mApplication.registerActivityLifecycleCallbacks(lifecycle);
        }

        //执行框架外部, 开发者扩展的 App onCreate 逻辑
        for (IAppDelegate lifecycle : mIAppLifecycles) {
            lifecycle.onCreate(mApplication);
        }

        //初始化日志打印
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));

    }


    private GlobalConfigModule handlerGlobalConfigModule(GlobalConfigModule.Builder builder){
        //遍历 ConfigModule 集合, 给全局配置 GlobalConfigModule 添加参数
        for (ConfigModule module : mModules) {
            module.applyOptions(mApplication, builder);
        }

        return builder.build();
    }


    @Override
    public void onTerminate(@NonNull Application application) {
        if (mActivityLifecycle != null) {
            mApplication.unregisterActivityLifecycleCallbacks(mActivityLifecycle);
        }
        if (mActivityLifecycleForRxLifecycle != null) {
            mApplication.unregisterActivityLifecycleCallbacks(mActivityLifecycleForRxLifecycle);
        }
        if (mActivityLifecycles != null && mActivityLifecycles.size() > 0) {
            for (Application.ActivityLifecycleCallbacks lifecycle : mActivityLifecycles) {
                mApplication.unregisterActivityLifecycleCallbacks(lifecycle);
            }
        }
        if (mIAppLifecycles != null && mIAppLifecycles.size() > 0) {
            for (IAppDelegate lifecycle : mIAppLifecycles) {
                lifecycle.onTerminate(mApplication);
            }
        }
        this.mAppComponent = null;
        this.mActivityLifecycle = null;
        this.mActivityLifecycleForRxLifecycle = null;
        this.mActivityLifecycles = null;
        this.mIAppLifecycles = null;
        this.mApplication = null;
    }



    @NonNull
    @Override
    public AppComponent getAppComponent() {
        PreconditionsUtil.checkNotNull(mAppComponent,
                "%s cannot be null,first call %s#onCreate(Application) in %s#onCreate()",
                AppComponent.class.getName(), getClass().getName(), Application.class.getName());
        return mAppComponent;
    }




}

