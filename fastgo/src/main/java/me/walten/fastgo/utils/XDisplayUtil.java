package me.walten.fastgo.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;


/**
 *
 * @author kehua
 * @date 2016-03-17
 */
public class XDisplayUtil {

    public static float dpToPx(Context context,float dp) {
        if (context == null) {
            return -1;
        }
        return dp * getDensity(context);
    }

    public static float pxToDp(Context context,int px) {
        if (context == null) {
            return -1;
        }
        return px / getDensity(context);
    }

    public static int dpToPxInt(Context context,float dp) {
        return (int)(dpToPx(context,dp) + 0.5f);
    }

    public static int pxToDpInt(Context context,int px) {
        return (int)(pxToDp(context,px) + 0.5f);
    }

    public static float spToPx(Context context,float dp) {
        if (context == null) {
            return -1;
        }
        return dp * context.getResources().getDisplayMetrics().scaledDensity;
    }

    public static float pxToSp(Context context, int px) {
        if (context == null) {
            return -1;
        }
        return px / context.getResources().getDisplayMetrics().scaledDensity;
    }

    public static int spToPxInt(Context context,float dp) {
        return (int)(spToPx(context,dp) + 0.5f);
    }

    public static int pxToSpInt(Context context,int px) {
        return (int)(pxToSp(context,px) + 0.5f);
    }

    /**
     * 屏幕密度
     */
    public static float sDensity = 0f;
    public static float getDensity(Context context){
        if(sDensity == 0f){
            sDensity = getDisplayMetrics(context).density;
        }
        return sDensity;
    }

    /**
     * DisplayMetrics
     * @return
     */
    public static DisplayMetrics getDisplayMetrics(Context context){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getApplicationContext().getSystemService(Context.WINDOW_SERVICE))
                .getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }
}
