package ${packageName}.configuration;

import android.app.Application;
import android.content.Context;
import android.support.v4.app.FragmentManager;

import java.util.List;

import me.walten.fastgo.delegate.IAppDelegate;
import ${packageName}.application.AppDelegateImpl;
import me.walten.fastgo.di.module.GlobalConfigModule;
import me.walten.fastgo.integration.ConfigModule;

public class GlobalConfiguration implements ConfigModule{
    @Override
    public void applyOptions(Context context, GlobalConfigModule.Builder builder) {
        // TODO: 可以在这边配置Retrofit Okhttp Logger
    }

    @Override
    public void injectAppLifecycle(Context context, List<IAppDelegate> lifecycles) {
        lifecycles.add(new AppDelegateImpl());
    }

    @Override
    public void injectActivityLifecycle(Context context, List<Application.ActivityLifecycleCallbacks> lifecycles) {
        // TODO: Activity生命周期订阅 全局的
    }

    @Override
    public void injectFragmentLifecycle(Context context, List<FragmentManager.FragmentLifecycleCallbacks> lifecycles) {
        // TODO: Fragment生命周期订阅 全局的
    }
}