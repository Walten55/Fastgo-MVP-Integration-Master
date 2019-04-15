package me.walten.fastgo.base.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle2.android.FragmentEvent;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;
import me.walten.fastgo.base.IPage;
import me.walten.fastgo.base.activitiy.SimpleActivity;
import me.walten.fastgo.base.mvp.BaseView;
import me.walten.fastgo.integration.lifecycle.FragmentLifecycleable;
import me.yokeyword.fragmentation.SupportFragment;

/*
 * -----------------------------------------------------------------
 * Copyright by Walten, All rights reserved.
 * -----------------------------------------------------------------
 * desc：
 * -----------------------------------------------------------------
 * 2018/5/24 : Create SimpleFragment.java (Walten);
 * -----------------------------------------------------------------
 */
public abstract class SimpleFragment extends SupportFragment implements BaseView,IPage ,FragmentLifecycleable {

    private final BehaviorSubject<FragmentEvent> mLifecycleSubject = BehaviorSubject.create();

    protected View mView;
    protected SimpleActivity mActivity;
    protected Context mContext;
    private Unbinder mUnBinder;
    protected boolean isInit = false;

    @Override
    public void onAttach(Context context) {
        if (context instanceof SimpleActivity) {
            mActivity = (SimpleActivity) context;
            mContext = context;
        } else {
            throw new IllegalStateException("SimpleFragment 必须 依赖于 SimpleActivity");
        }

        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(getLayoutResId(), null);
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUnBinder = ButterKnife.bind(this, view);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        if (!isInit) {
            initView(savedInstanceState);
            initData(savedInstanceState);
            isInit = true;
        }
    }

    @Override
    public void onDestroy() {
        mUnBinder.unbind();
        super.onDestroy();
    }

    /**
     * 提示框
     *
     * @param msg
     */
    public void showTipDialog(int opsStatus, String msg) {

    }

    /**
     * 提示框
     *
     * @param msg
     */
    public void showTipDialog(String msg) {

    }

    /**
     * 加载框
     *
     * @param msg
     */
    public void statusLoading(String msg) {

    }

    /**
     * 吐司
     *
     * @param opsStatus
     * @param msg
     */
    public void showToast(int opsStatus, String msg) {

    }

    /**
     * 吐司
     *
     * @param msg
     */
    public void showToast(String msg) {

    }

    /**
     * 加载框
     * @param msg
     */
    @Override
    public void startWaiting(String msg) {
        mActivity.startWaiting(msg);
    }

    /**
     * 隐藏加载框
     */
    @Override
    public void stopWaiting() {
        mActivity.stopWaiting();
    }

    @NonNull
    @Override
    public Subject<FragmentEvent> provideLifecycleSubject() {
        return mLifecycleSubject;
    }

    @Override
    public void finishView() {
        getActivity().finish();
    }
}
