package me.walten.fastgo.di.module;

import android.app.Application;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.io.File;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.walten.fastgo.common.Constants;
import okhttp3.HttpUrl;

/*
 * -----------------------------------------------------------------
 * Copyright by Walten, All rights reserved.
 * -----------------------------------------------------------------
 * desc：
 * -----------------------------------------------------------------
 * 2018/5/24 : Create GlobalConfigModule.java (Walten);
 * -----------------------------------------------------------------
 */
@Module
public class GlobalConfigModule {
    private HttpUrl mApiUrl;
    private File mCacheFile;
    private ThirdPartyModule.RetrofitConfiguration mRetrofitConfiguration;
    private ThirdPartyModule.OkhttpConfiguration mOkhttpConfiguration;
    private ThirdPartyModule.LoggerConfiguration mLoggerConfiguration;

    private GlobalConfigModule(Builder builder) {
        this.mApiUrl = builder.apiUrl;
        this.mCacheFile = builder.cacheFile;
        this.mRetrofitConfiguration = builder.retrofitConfiguration;
        this.mOkhttpConfiguration = builder.okhttpConfiguration;
        this.mLoggerConfiguration = builder.loggerConfiguration;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * 提供 BaseUrl,默认使用 <"https://api.github.com/">
     *
     * @return
     */
    @Singleton
    @Provides
    HttpUrl provideBaseUrl() {
        return mApiUrl == null ? HttpUrl.parse("https://api.github.com/") : mApiUrl;
    }


    /**
     * 提供缓存文件
     */
    @Singleton
    @Provides
    File provideCacheFile(Application application) {
        return mCacheFile == null ? new File(application.getCacheDir().getAbsolutePath()+File.separator+ Constants.FILE_NAME_NET_CACHE) : mCacheFile;
    }


    @Singleton
    @Provides
    @Nullable
    ThirdPartyModule.RetrofitConfiguration provideRetrofitConfiguration() {
        return mRetrofitConfiguration;
    }

    @Singleton
    @Provides
    @Nullable
    ThirdPartyModule.OkhttpConfiguration provideOkhttpConfiguration() {
        return mOkhttpConfiguration;
    }

    @Singleton
    @Provides
    @Nullable
    ThirdPartyModule.LoggerConfiguration provideLoggerConfiguration() {
        return mLoggerConfiguration;
    }


    public static final class Builder {
        private HttpUrl apiUrl;
        private File cacheFile;
        private ThirdPartyModule.RetrofitConfiguration retrofitConfiguration;
        private ThirdPartyModule.OkhttpConfiguration okhttpConfiguration;
        private ThirdPartyModule.LoggerConfiguration loggerConfiguration;

        private Builder() {
        }

        public Builder baseurl(String baseUrl) {//基础url
            if (TextUtils.isEmpty(baseUrl)) {
                throw new NullPointerException("BaseUrl can not be empty");
            }
            this.apiUrl = HttpUrl.parse(baseUrl);
            return this;
        }

        public Builder cacheFile(File cacheFile) {
            this.cacheFile = cacheFile;
            return this;
        }

        public Builder retrofitConfiguration(ThirdPartyModule.RetrofitConfiguration retrofitConfiguration) {
            this.retrofitConfiguration = retrofitConfiguration;
            return this;
        }

        public Builder okhttpConfiguration(ThirdPartyModule.OkhttpConfiguration okhttpConfiguration) {
            this.okhttpConfiguration = okhttpConfiguration;
            return this;
        }

        public Builder loggerConfiguration(ThirdPartyModule.LoggerConfiguration loggerConfiguration) {
            this.loggerConfiguration = loggerConfiguration;
            return this;
        }

        public GlobalConfigModule build() {
            return new GlobalConfigModule(this);
        }


    }
}
