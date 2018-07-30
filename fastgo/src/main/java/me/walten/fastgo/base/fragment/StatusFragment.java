package me.walten.fastgo.base.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import me.walten.fastgo.R;
import me.walten.fastgo.base.mvp.IPresenter;
import me.walten.fastgo.base.mvp.DataStatusView;
import me.walten.fastgo.utils.XPreconditionsUtil;

/*
 * -----------------------------------------------------------------
 * Copyright by Walten, All rights reserved.
 * -----------------------------------------------------------------
 * desc：
 * -----------------------------------------------------------------
 * 2018/5/24 : Create StatusFragment.java (Walten);
 * -----------------------------------------------------------------
 */
public abstract class StatusFragment<T extends IPresenter> extends MVPFragment<T> implements DataStatusView {

    public static final int STATUS_SUCCESS = 0x00;
    public static final int STATUS_LOADING = 0x01;
    public static final int STATUS_ERROR = 0x02;

    private View viewError;
    private View viewLoading;
    private View viewMain;
    private ViewGroup mParent;

    private int mErrorResource = R.layout.view_error;

    private int currentSTATUS = STATUS_SUCCESS;
    private boolean isErrorViewAdded = false;

    /**
     * 获取主布局Id
     * @return
     */
    protected abstract int getMainViewId();

    /**
     *
     * @return int[2]  0:emptyImageId 1:errorImageId
     */
    protected int[] getStatusImageId(){
        return new int[]{R.drawable.icon_cammon_data,R.drawable.icon_cammon_load};
    }

    /**
     *
     * @return int[2]  0:emptyMessage 1:errorMessage
     */
    protected String[] getStatusMessage(){
        return new String[]{"<font color=\"#bdbdbd\">暂无数据,</font><font color=\"#e83535\">刷新试试</font>",
                "<font color=\"#bdbdbd\">加载失败,</font><font color=\"#e83535\">重新加载</font>"};
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        if (getView() == null)
            return;
        viewMain = getView().findViewById(getMainViewId());

        XPreconditionsUtil.checkNotNull(viewMain,"必须提供有效的主布局ID");
        XPreconditionsUtil.checkState(viewMain.getParent() instanceof ViewGroup, "'主布局'的父布局必须是ViewGroup");

        mParent = (ViewGroup) viewMain.getParent();
        View.inflate(mContext, R.layout.view_progress, mParent);
        viewLoading = mParent.findViewById(R.id.view_loading);
        viewLoading.setVisibility(View.GONE);
        viewMain.setVisibility(View.VISIBLE);
    }

    /**
     * 重新加载
     */
    protected void reTry(){

    }

    @Override
    public void statusError() {
        errorOrEmpty(false);
    }

    @Override
    public void statusEmpty() {
        errorOrEmpty(true);
    }

    private void errorOrEmpty(boolean empty){
        if (currentSTATUS == STATUS_ERROR)
            return;
        if (!isErrorViewAdded) {
            isErrorViewAdded = true;
            View.inflate(mContext, mErrorResource, mParent);
            viewError = mParent.findViewById(R.id.view_error);
            XPreconditionsUtil.checkNotNull(viewError,"'view_error' 布局错误");
        }
        hideCurrentView();
        currentSTATUS = STATUS_ERROR;
        ImageView errorIcon = (ImageView) viewError.findViewById(R.id.error_icon);
        TextView errorMsg = (TextView) viewError.findViewById(R.id.error_message);
        if(empty){
            String msg = getStatusMessage()[0];
            errorIcon.setImageResource(getStatusImageId()[0]);
            errorMsg.setText(Html.fromHtml(msg));
        }else {
            String msg = getStatusMessage()[1];
            errorIcon.setImageResource(getStatusImageId()[1]);
            errorMsg.setText(Html.fromHtml(msg));
        }
        viewError.setVisibility(View.VISIBLE);
        viewError.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reTry();
            }
        });
    }

    @Override
    public void statusLoadingInLayout(String msg) {
        if (currentSTATUS == STATUS_LOADING)
            return;
        hideCurrentView();
        currentSTATUS = STATUS_LOADING;
        viewLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void statusSuccess() {
        if (currentSTATUS == STATUS_SUCCESS)
            return;
        hideCurrentView();
        currentSTATUS = STATUS_SUCCESS;
        viewMain.setVisibility(View.VISIBLE);
    }

    private void hideCurrentView() {
        switch (currentSTATUS) {
            case STATUS_SUCCESS:
                viewMain.setVisibility(View.GONE);
                break;
            case STATUS_LOADING:
                viewLoading.setVisibility(View.GONE);
                break;
            case STATUS_ERROR:
                if (viewError != null) {
                    viewError.setVisibility(View.GONE);
                }
                break;
        }
    }
}
