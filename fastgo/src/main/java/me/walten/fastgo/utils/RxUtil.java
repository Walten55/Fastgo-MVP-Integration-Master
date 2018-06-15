package me.walten.fastgo.utils;

import org.reactivestreams.Publisher;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/*
 * -----------------------------------------------------------------
 * Copyright by 2018 Walten, All rights reserved.
 * -----------------------------------------------------------------
 * desc:
 * -----------------------------------------------------------------
 * 2018/5/28 : Create RxUtil.java (Walten);
 * -----------------------------------------------------------------
 */
public class RxUtil {

    /**
     * 默认网络请求线程是io线程 切换 main线程
     * @param <R>
     * @return
     */
    public static <R> FlowableTransformer<R,R> getHttpDefaultScheduler(){
        return new FlowableTransformer<R, R>() {
            @Override
            public Publisher<R> apply(Flowable<R> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     *
     * @param r
     * @param <R>
     * @return
     */
    public static <R> Flowable<R> formatResult(final R r){
        return Flowable.create(new FlowableOnSubscribe<R>() {
            @Override
            public void subscribe(FlowableEmitter<R> emitter) throws Exception {
                try {
                    emitter.onNext(r);
                    emitter.onComplete();
                }catch (Exception e){
                    emitter.onError(e);
                }
            }
        }, BackpressureStrategy.BUFFER);
    }


    public static <R,T> FlowableTransformer<R,T> getHandleResultDefault(final Function<R, Flowable<T>> flatMap){
        return new FlowableTransformer<R, T>() {
            @Override
            public Flowable<T> apply(Flowable<R> httpResponseFlowable) {
                return httpResponseFlowable.flatMap(flatMap);
            }
        };
    }
}
