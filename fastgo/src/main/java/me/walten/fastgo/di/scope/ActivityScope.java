package me.walten.fastgo.di.scope;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/*
 * -----------------------------------------------------------------
 * Copyright by Walten, All rights reserved.
 * -----------------------------------------------------------------
 * desc：
 * -----------------------------------------------------------------
 * 2018/5/24 : Create ActivityScope.java (Walten);
 * -----------------------------------------------------------------
 */
@Scope
@Retention(RUNTIME)
public @interface ActivityScope {
}
