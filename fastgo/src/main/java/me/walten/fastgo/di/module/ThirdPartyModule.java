package me.walten.fastgo.di.module;

import android.app.Application;
import android.content.Context;
import android.support.annotation.Nullable;

import com.blankj.utilcode.util.NetworkUtils;
import com.google.gson.Gson;
import com.orhanobut.logger.PrettyFormatStrategy;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.walten.fastgo.BuildConfig;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/*
 * -----------------------------------------------------------------
 * Copyright by Walten, All rights reserved.
 * -----------------------------------------------------------------
 * desc：
 * -----------------------------------------------------------------
 * 2018/5/24 : Create ThirdPartyModule.java (Walten);
 * -----------------------------------------------------------------
 */
@Module
public class ThirdPartyModule {
    /**
     * 提供 {@link Retrofit}
     *
     * @param application
     * @param configuration
     * @param builder
     * @param client
     * @param httpUrl
     * @return {@link Retrofit}
     */
    @Singleton
    @Provides
    Retrofit provideRetrofit(Application application,
                             @Nullable RetrofitConfiguration configuration,
                             Retrofit.Builder builder,
                             OkHttpClient client,
                             HttpUrl httpUrl) {


        builder
                .baseUrl(httpUrl)//域名
                .client(client);//设置okhttp

        if (configuration != null) {
            configuration.configRetrofit(application, builder);
        } else {
            builder
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//使用 Rxjava
                    .addConverterFactory(GsonConverterFactory.create(new Gson()));//使用 Gson
        }

        return builder.build();
    }

    /**
     * 提供 {@link OkHttpClient}
     *
     * @param application
     * @param cacheFile
     * @param builder
     * @param configuration
     * @return {@link OkHttpClient}
     */
    @Singleton
    @Provides
    OkHttpClient provideClient(Application application,
                               File cacheFile,
                               OkHttpClient.Builder builder,
                               @Nullable OkhttpConfiguration configuration) {

        if (configuration != null)
            configuration.configOkhttp(application, builder);
        else {
            if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                builder.addInterceptor(loggingInterceptor);
            }
            Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
            Interceptor cacheInterceptor = new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request();
                    if (!NetworkUtils.isConnected()) {
                        request = request.newBuilder()
                                .cacheControl(CacheControl.FORCE_CACHE)
                                .build();
                    }
                    Response response = chain.proceed(request);
                    if (NetworkUtils.isConnected()) {
                        int maxAge = 0;
                        // 有网络时, 不缓存, 最大保存时长为0
                        response.newBuilder()
                                .header("Cache-Control", "public, max-age=" + maxAge)
                                .removeHeader("Pragma")
                                .build();
                    } else {
                        // 无网络时，设置超时为4周
                        int maxStale = 60 * 60 * 24 * 28;
                        response.newBuilder()
                                .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                                .removeHeader("Pragma")
                                .build();
                    }
                    return response;
                }
            };
            //设置缓存
            builder.addNetworkInterceptor(cacheInterceptor);
            builder.addInterceptor(cacheInterceptor);
            builder.cache(cache);
            //设置超时
            builder.connectTimeout(10, TimeUnit.SECONDS);
            builder.readTimeout(20, TimeUnit.SECONDS);
            builder.writeTimeout(20, TimeUnit.SECONDS);
            //错误重连
            builder.retryOnConnectionFailure(true);
        }
        return builder.build();
    }

    @Singleton
    @Provides
    PrettyFormatStrategy provideFormatStrategy(Application application,
                                    @Nullable LoggerConfiguration configuration,
                                            PrettyFormatStrategy.Builder builder) {

        if (configuration != null) {
            configuration.configLogger(application,builder);
        } else {
            builder.tag("Fastgo_Logger");
        }

        return builder.build();
    }

    @Singleton
    @Provides
    Retrofit.Builder provideRetrofitBuilder() {
        return new Retrofit.Builder();
    }

    @Singleton
    @Provides
    OkHttpClient.Builder provideClientBuilder() {
        return new OkHttpClient.Builder();
    }

    @Singleton
    @Provides
    PrettyFormatStrategy.Builder provideFormatStrategyBuilder() {
        return PrettyFormatStrategy.newBuilder();
    }

    public interface RetrofitConfiguration {
        void configRetrofit(Context context, Retrofit.Builder builder);
    }

    public interface OkhttpConfiguration {
        void configOkhttp(Context context, OkHttpClient.Builder builder);
    }

    public interface LoggerConfiguration {
        void configLogger(Context context, PrettyFormatStrategy.Builder builder);
    }

}
