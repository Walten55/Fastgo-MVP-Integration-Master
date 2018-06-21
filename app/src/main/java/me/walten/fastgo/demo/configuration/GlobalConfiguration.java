package me.walten.fastgo.demo.configuration;

import android.app.Application;
import android.content.Context;
import android.support.v4.app.FragmentManager;

import com.orhanobut.logger.PrettyFormatStrategy;

import java.util.List;

import me.walten.fastgo.delegate.IAppDelegate;
import me.walten.fastgo.demo.application.AppDelegateImpl;
import me.walten.fastgo.di.module.GlobalConfigModule;
import me.walten.fastgo.di.module.ThirdPartyModule;
import me.walten.fastgo.integration.ConfigModule;

public class GlobalConfiguration implements ConfigModule {
    @Override
    public void applyOptions(Context context, GlobalConfigModule.Builder builder) {
        builder.baseurl(Constant.BASE_URL);

        // TODO: 可以在这边配置Retrofit Okhttp Logger
        builder.loggerConfiguration(new ThirdPartyModule.LoggerConfiguration() {
            @Override
            public void configLogger(Context context, PrettyFormatStrategy.Builder builder) {
                builder.methodOffset(0)
                        .methodCount(0)
                        .showThreadInfo(false)
                        .tag("DEMO");

            }
        });

    }

    @Override
    public void injectAppLifecycle(Context context, List<IAppDelegate> lifecycles) {
        lifecycles.add(new AppDelegateImpl());
    }

    @Override
    public void injectActivityLifecycle(Context context, List<Application.ActivityLifecycleCallbacks> lifecycles) {
//        lifecycles.add(new Application.ActivityLifecycleCallbacks() {
//            @Override
//            public void onActivityCreated(Activity activity, Bundle bundle) {
//                Logger.e("onActivityCreated");
//            }
//
//            @Override
//            public void onActivityStarted(Activity activity) {
//                Logger.e("onActivityStarted");
//            }
//
//            @Override
//            public void onActivityResumed(Activity activity) {
//                Logger.e("onActivityResumed");
//            }
//
//            @Override
//            public void onActivityPaused(Activity activity) {
//                Logger.e("onActivityPaused");
//            }
//
//            @Override
//            public void onActivityStopped(Activity activity) {
//                Logger.e("onActivityStopped");
//            }
//
//            @Override
//            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
//                Logger.e("onActivitySaveInstanceState");
//            }
//
//            @Override
//            public void onActivityDestroyed(Activity activity) {
//                Logger.e("onActivityDestroyed");
//            }
//        });
    }

    @Override
    public void injectFragmentLifecycle(Context context, List<FragmentManager.FragmentLifecycleCallbacks> lifecycles) {
//        lifecycles.add(new FragmentManager.FragmentLifecycleCallbacks() {
//            @Override
//            public void onFragmentPreAttached(FragmentManager fm, Fragment f, Context context) {
//                super.onFragmentPreAttached(fm, f, context);
//                Logger.e("onFragmentPreAttached");
//            }
//
//            @Override
//            public void onFragmentAttached(FragmentManager fm, Fragment f, Context context) {
//                super.onFragmentAttached(fm, f, context);
//                Logger.e("onFragmentAttached");
//            }
//
//            @Override
//            public void onFragmentPreCreated(FragmentManager fm, Fragment f, Bundle savedInstanceState) {
//                super.onFragmentPreCreated(fm, f, savedInstanceState);
//                Logger.e("onFragmentPreCreated");
//            }
//
//            @Override
//            public void onFragmentCreated(FragmentManager fm, Fragment f, Bundle savedInstanceState) {
//                super.onFragmentCreated(fm, f, savedInstanceState);
//                Logger.e("onFragmentCreated");
//            }
//
//            @Override
//            public void onFragmentActivityCreated(FragmentManager fm, Fragment f, Bundle savedInstanceState) {
//                super.onFragmentActivityCreated(fm, f, savedInstanceState);
//                Logger.e("onFragmentActivityCreated");
//            }
//
//            @Override
//            public void onFragmentViewCreated(FragmentManager fm, Fragment f, View v, Bundle savedInstanceState) {
//                super.onFragmentViewCreated(fm, f, v, savedInstanceState);
//                Logger.e("onFragmentViewCreated");
//            }
//
//            @Override
//            public void onFragmentStarted(FragmentManager fm, Fragment f) {
//                super.onFragmentStarted(fm, f);
//                Logger.e("onFragmentStarted");
//            }
//
//            @Override
//            public void onFragmentResumed(FragmentManager fm, Fragment f) {
//                super.onFragmentResumed(fm, f);
//                Logger.e("onFragmentResumed");
//            }
//
//            @Override
//            public void onFragmentPaused(FragmentManager fm, Fragment f) {
//                super.onFragmentPaused(fm, f);
//                Logger.e("onFragmentPaused");
//            }
//
//            @Override
//            public void onFragmentStopped(FragmentManager fm, Fragment f) {
//                super.onFragmentStopped(fm, f);
//                Logger.e("onFragmentStopped");
//            }
//
//            @Override
//            public void onFragmentSaveInstanceState(FragmentManager fm, Fragment f, Bundle outState) {
//                super.onFragmentSaveInstanceState(fm, f, outState);
//                Logger.e("onFragmentSaveInstanceState");
//            }
//
//            @Override
//            public void onFragmentViewDestroyed(FragmentManager fm, Fragment f) {
//                super.onFragmentViewDestroyed(fm, f);
//                Logger.e("onFragmentViewDestroyed");
//            }
//
//            @Override
//            public void onFragmentDestroyed(FragmentManager fm, Fragment f) {
//                super.onFragmentDestroyed(fm, f);
//                Logger.e("onFragmentDestroyed");
//            }
//
//            @Override
//            public void onFragmentDetached(FragmentManager fm, Fragment f) {
//                super.onFragmentDetached(fm, f);
//                Logger.e("onFragmentDetached");
//
//                //记得调用 防止内存泄露
//                fm.unregisterFragmentLifecycleCallbacks(this);
//            }
//        });
    }
}