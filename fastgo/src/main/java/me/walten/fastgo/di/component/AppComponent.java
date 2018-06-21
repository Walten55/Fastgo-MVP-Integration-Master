package me.walten.fastgo.di.component;

import android.app.Application;

import java.io.File;

import javax.inject.Singleton;

import dagger.Component;
import me.walten.fastgo.common.Fastgo;
import me.walten.fastgo.delegate.AppDelegateImpl;
import me.walten.fastgo.di.module.AppModule;
import me.walten.fastgo.di.module.GlobalConfigModule;
import me.walten.fastgo.di.module.ThirdPartyModule;
import me.walten.fastgo.integration.IRepositoryManager;
import retrofit2.Retrofit;

/*
 * -----------------------------------------------------------------
 * Copyright by Walten, All rights reserved.
 * -----------------------------------------------------------------
 * desc：
 * -----------------------------------------------------------------
 * 2018/5/24 : Create AppComponent.java (Walten);
 * -----------------------------------------------------------------
 */
@Singleton
@Component(modules = {AppModule.class, ThirdPartyModule.class, GlobalConfigModule.class})
public interface AppComponent {

    /**
     * 应用实例
     *
     * @return
     */
    Application application();

    /**
     * 网络请求客户端
     *
     * @return
     */
    Retrofit retrofit();

    /**
     * 缓存文件
     *
     * @return File
     */
    File cacheFile();

    /**
     * 数据仓库
     * @return
     */
    IRepositoryManager repositoryManager();

    /**
     * 注入到APP代理
     *
     * @param appDelegate
     */
    void inject(AppDelegateImpl appDelegate);

    /**
     * 注入到Fastgo
     *
     * @param fastgo
     */
    void inject(Fastgo fastgo);
}
