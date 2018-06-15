package me.walten.fastgo.integration.lifecycle;

import android.support.annotation.NonNull;


import io.reactivex.subjects.Subject;
/*
 * -----------------------------------------------------------------
 * Copyright by Walten, All rights reserved.
 * -----------------------------------------------------------------
 * desc：
 * -----------------------------------------------------------------
 * 2018/5/24 : Create Lifecycleable.java (Walten);
 * -----------------------------------------------------------------
 */
public interface Lifecycleable<E> {
    @NonNull
    Subject<E> provideLifecycleSubject();
}
