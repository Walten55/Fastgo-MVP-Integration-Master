/*
 * Copyright 2017 JessYan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package me.walten.fastgo.integration;

import android.app.Application;
import android.content.Context;

import javax.inject.Inject;

import dagger.Lazy;
import me.walten.fastgo.cache.LruCache;
import me.walten.fastgo.utils.XPreconditionsUtil;
import retrofit2.Retrofit;

/*
 * -----------------------------------------------------------------
 * Copyright by Walten, All rights reserved.
 * -----------------------------------------------------------------
 * desc：
 * -----------------------------------------------------------------
 * 2018/5/24 : Create RepositoryManager.java (Walten);
 * -----------------------------------------------------------------
 */
public class RepositoryManager implements IRepositoryManager {
    @Inject
    Lazy<Retrofit> mRetrofit;
    @Inject
    Application mApplication;
    @Inject
    Lazy<LruCache<String,Object>> mCache;

    private final String CACHE_KEY_RETROFIT = "RETROFIT";

    @Inject
    public RepositoryManager() {
    }

    /**
     * 根据传入的 Class 获取对应的 Retrofit service
     *
     * @param service
     * @param <T>
     * @return
     */
    @Override
    public synchronized <T> T obtainRetrofitService(Class<T> service) {
        XPreconditionsUtil.checkNotNull(service,"service must be not null");
        if(mCache.get().get(CACHE_KEY_RETROFIT+service.getCanonicalName())==null){
            mCache.get().put(CACHE_KEY_RETROFIT+service.getCanonicalName(),mRetrofit.get().create(service));
        }

        return (T) mCache.get().get(CACHE_KEY_RETROFIT+service.getCanonicalName());
    }

    @Override
    public Context getContext() {
        return mApplication;
    }
}
