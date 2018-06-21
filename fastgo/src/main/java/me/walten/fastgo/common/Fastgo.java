package me.walten.fastgo.common;

import android.app.Application;

import java.lang.ref.WeakReference;

/*
 * -----------------------------------------------------------------
 * Copyright by 2018 Walten, All rights reserved.
 * -----------------------------------------------------------------
 * desc:
 * -----------------------------------------------------------------
 * 2018/6/21 : Create Fastgo.java (Walten);
 * -----------------------------------------------------------------
 */
public final class Fastgo {
    private static boolean isPrintLog;

    private static WeakReference<Application> mContext;


    public static void printLog(boolean print){
        isPrintLog = print;
    }

    public static boolean isPrintLog(){
        return isPrintLog;
    }

    public static void init(Application application){
        if(mContext == null){
            mContext = new WeakReference<Application>(application);
        }
    }

    public static Application getContext(){
        return mContext==null? null:mContext.get();
    }

    public static void release(){
        if(mContext!=null){
            mContext.clear();
        }
    }
}
