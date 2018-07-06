package me.walten.fastgo.utils;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.RxLifecycle;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.trello.rxlifecycle2.android.RxLifecycleAndroid;

import io.reactivex.annotations.NonNull;
import me.walten.fastgo.base.mvp.BaseView;
import me.walten.fastgo.integration.lifecycle.ActivityLifecycleable;
import me.walten.fastgo.integration.lifecycle.FragmentLifecycleable;
import me.walten.fastgo.integration.lifecycle.Lifecycleable;

/*
 * -----------------------------------------------------------------
 * Copyright by Walten, All rights reserved.
 * -----------------------------------------------------------------
 * desc：
 * -----------------------------------------------------------------
 * 2018/5/24 : Create RxLifecycleUtil.java (Walten);
 * -----------------------------------------------------------------
 */
public class XRxLifecycleUtil {

    private XRxLifecycleUtil() {
        throw new IllegalStateException("you can't instantiate me!");
    }

    /**
     * 绑定 Activity 的指定生命周期
     *
     * @param view
     * @param event
     * @param <T>
     * @return
     */
    public static <T> LifecycleTransformer<T> bindUntilEvent(@NonNull final BaseView view,
                                                             final ActivityEvent event) {
        XPreconditionsUtil.checkNotNull(view, "view == null");
        if (view instanceof ActivityLifecycleable) {
            return bindUntilEvent((ActivityLifecycleable) view, event);
        } else {
            throw new IllegalArgumentException("view isn't ActivityLifecycleable");
        }
    }

    /**
     * 绑定 Fragment 的指定生命周期
     *
     * @param view
     * @param event
     * @param <T>
     * @return
     */
    public static <T> LifecycleTransformer<T> bindUntilEvent(@NonNull final BaseView view,
                                                             final FragmentEvent event) {
        XPreconditionsUtil.checkNotNull(view, "view == null");
        if (view instanceof FragmentLifecycleable) {
            return bindUntilEvent((FragmentLifecycleable) view, event);
        } else {
            throw new IllegalArgumentException("view isn't FragmentLifecycleable");
        }
    }

    /**
     *
     * @param lifecycleable
     * @param event
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T, R> LifecycleTransformer<T> bindUntilEvent(@NonNull final Lifecycleable<R> lifecycleable,
                                                                final R event) {
        XPreconditionsUtil.checkNotNull(lifecycleable, "lifecycleable == null");
        return RxLifecycle.bindUntilEvent(lifecycleable.provideLifecycleSubject(), event);
    }


    /**
     * 绑定 Activity/Fragment 的生命周期
     *
     * @param view
     * @param <T>
     * @return
     */
    public static <T> LifecycleTransformer<T> bindToLifecycle(@NonNull BaseView view) {
        XPreconditionsUtil.checkNotNull(view, "view == null");
        if (view instanceof Lifecycleable) {
            return bindToLifecycle((Lifecycleable) view);
        } else {
            throw new IllegalArgumentException("view isn't Lifecycleable");
        }
    }

    /**
     *
     * @param lifecycleable
     * @param <T>
     * @return
     */
    public static <T> LifecycleTransformer<T> bindToLifecycle(@NonNull Lifecycleable lifecycleable) {
        XPreconditionsUtil.checkNotNull(lifecycleable, "lifecycleable == null");
        if (lifecycleable instanceof ActivityLifecycleable) {
            return RxLifecycleAndroid.bindActivity(((ActivityLifecycleable) lifecycleable).provideLifecycleSubject());
        } else if (lifecycleable instanceof FragmentLifecycleable) {
            return RxLifecycleAndroid.bindFragment(((FragmentLifecycleable) lifecycleable).provideLifecycleSubject());
        } else {
            throw new IllegalArgumentException("Lifecycleable not match");
        }
    }

}