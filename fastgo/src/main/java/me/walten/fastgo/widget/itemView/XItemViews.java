package me.walten.fastgo.widget.itemView;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

/*
 * -----------------------------------------------------------------
 * Copyright by 2018 Walten, All rights reserved.
 * -----------------------------------------------------------------
 * desc:
 * -----------------------------------------------------------------
 * 2018/7/6 : Create XItemView.java (Walten);
 * -----------------------------------------------------------------
 */
public class XItemViews extends RecyclerView{
    private BaseQuickAdapter mAdapter;

    public XItemViews(Context context) {
        super(context);
    }

    public XItemViews(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public XItemViews(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setData(List<SimpleItem> data){
        if(data == null)
            return;

        if(mAdapter == null&&data.size()>0){
            mAdapter = new ItemViewAdapter(data);
            setLayoutManager(new LinearLayoutManager(getContext()));
            setAdapter(mAdapter);
        }else if(mAdapter != null){
            mAdapter.setNewData(data);
            mAdapter.notifyDataSetChanged();
        }
    }

    public List<SimpleItem> getData(){
        if(mAdapter !=null)
            return mAdapter.getData();
        return null;
    }
}
