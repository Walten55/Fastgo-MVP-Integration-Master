package me.walten.fastgo.widget.itemView;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import me.walten.fastgo.R;

/*
 * -----------------------------------------------------------------
 * Copyright by 2018 Walten, All rights reserved.
 * -----------------------------------------------------------------
 * desc:
 * -----------------------------------------------------------------
 * 2018/7/6 : Create ItemViewAdapter.java (Walten);
 * -----------------------------------------------------------------
 */
public class ItemViewAdapter extends BaseQuickAdapter<SimpleItem,BaseViewHolder>{

    public ItemViewAdapter(@Nullable List<SimpleItem> data) {
        super(R.layout.item_view,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SimpleItem item) {
        helper.setGone(R.id.line_top,item.isShowTopLine());
        helper.setGone(R.id.line_bottom,item.isShowBottomLine());
        helper.setGone(R.id.iv_icon_item,item.getIcon()!=0);
        helper.setGone(R.id.iv_icon_right,item.getMoreIcon()!=0);

        if(item.getIcon()!=0)
            helper.setImageResource(R.id.iv_icon_item,item.getIcon());

        if(item.getMoreIcon()!=0)
            helper.setImageResource(R.id.iv_icon_right,item.getMoreIcon());

        helper.setText(R.id.tv_item_name,item.getItemName());
        helper.setText(R.id.tv_item_value,item.getItemValue());

        helper.setBackgroundColor(R.id.line_top,item.getLineColor());
        helper.setBackgroundColor(R.id.line_bottom,item.getLineColor());
    }
}
