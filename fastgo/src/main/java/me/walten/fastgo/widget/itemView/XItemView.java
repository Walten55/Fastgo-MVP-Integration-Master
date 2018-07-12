package me.walten.fastgo.widget.itemView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.kyleduo.switchbutton.SwitchButton;

import me.walten.fastgo.R;

/*
 * -----------------------------------------------------------------
 * Copyright by 2018 Walten, All rights reserved.
 * -----------------------------------------------------------------
 * desc:
 * -----------------------------------------------------------------
 * 2018/7/6 : Create XItemView.java (Walten);
 * -----------------------------------------------------------------
 */
public class XItemView extends FrameLayout{

    private View item;

    private ImageView mIconView;

    private View mTopLine;

    private View mBottomLine;

    private TextView mItemNameView;

    private TextView mItemValueView;

    private ImageView mMoreIconView;

    private ViewStub stubSwitch;

    private SwitchButton mSwitchButton;

    private Drawable icon;

    private String itemName = "";

    private String itemValue = "";

    private Drawable moreIcon;

    private Type type;

    private boolean showTopLine;

    private boolean showBottomLine;

    private int itemNameColor;

    private int itemValueColor;

    private int backgroundColor;

    private int lineColor;

    private enum Type {
        NO_ONE(0),
        SINGLE_TEXT(1),
        SINGLE_MORE_ICON(2),
        TEXT_AND_MORE_ICON(3),
        SWITCH_BUTTON(4);

        private int value;

        Type(int value) {
            this.value = value;
        }

        public  static Type getType(int value) {
            switch (value) {
                case 0:
                    return Type.NO_ONE;
                case 1:
                    return Type.SINGLE_TEXT;
                case 2:
                    return Type.SINGLE_MORE_ICON;
                case 3:
                    return Type.TEXT_AND_MORE_ICON;
                case 4:
                    return Type.SWITCH_BUTTON;
            }
            return Type.NO_ONE;
        }

    }

    public XItemView(Context context) {
        this(context,null);
    }

    public XItemView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public XItemView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    private void init(AttributeSet attrs){
        if(attrs!=null){
            final TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.XItemView);
            icon = a.getDrawable(R.styleable.XItemView_XItemView_icon);
            itemName = a.getString(R.styleable.XItemView_XItemView_item_name);
            itemValue = a.getString(R.styleable.XItemView_XItemView_item_value);
            moreIcon = a.getDrawable(R.styleable.XItemView_XItemView_more_icon);
            showTopLine = a.getBoolean(R.styleable.XItemView_XItemView_show_top_line,false);
            showBottomLine = a.getBoolean(R.styleable.XItemView_XItemView_show_bottom_line,true);
            itemNameColor = a.getColor(R.styleable.XItemView_XItemView_item_name_color,Color.parseColor("#5c5c5c"));
            itemValueColor = a.getColor(R.styleable.XItemView_XItemView_item_value_color,Color.parseColor("#cccccc"));
            backgroundColor = a.getColor(R.styleable.XItemView_XItemView_background,Color.parseColor("#ffffff"));
            lineColor = a.getColor(R.styleable.XItemView_XItemView_line_color, Color.parseColor("#e1e1e1"));
            type = Type.getType(a.getInt(R.styleable.XItemView_XItemView_type, 0));
            a.recycle();
        }

        addView(LayoutInflater.from(getContext()).inflate(R.layout.item_view,null));

        update();
    }

    private void update(){
        item = findViewById(R.id.rl_item);
        mIconView = findViewById(R.id.iv_icon_item);
        mItemNameView = findViewById(R.id.tv_item_name);
        mItemValueView = findViewById(R.id.tv_item_value);
        mMoreIconView = findViewById(R.id.iv_icon_right);
        mTopLine = findViewById(R.id.line_top);
        mBottomLine = findViewById(R.id.line_bottom);
        stubSwitch = findViewById(R.id.vs_switch);

        //颜色
        mTopLine.setBackgroundColor(lineColor);
        mBottomLine.setBackgroundColor(lineColor);
        mItemNameView.setTextColor(itemNameColor);
        mItemValueView.setTextColor(itemValueColor);
        item.setBackgroundColor(backgroundColor);

        //
        mTopLine.setVisibility(showTopLine?View.VISIBLE:View.GONE);
        mBottomLine.setVisibility(showBottomLine?View.VISIBLE:View.GONE);
        if(icon!=null){
            mIconView.setVisibility(View.VISIBLE);
            mIconView.setImageDrawable(icon);
        } else {
            mIconView.setVisibility(View.GONE);
        }

        if(mSwitchButton!=null)
            mSwitchButton.setVisibility(View.GONE);

        mItemNameView.setText(itemName);

        if(type == Type.NO_ONE){
            mMoreIconView.setVisibility(View.GONE);
            mItemValueView.setVisibility(View.GONE);
        }else if(type == Type.SINGLE_TEXT){
            mMoreIconView.setVisibility(View.GONE);
            mItemValueView.setVisibility(View.VISIBLE);
            mItemValueView.setText(itemValue);
        }else if(type == Type.SINGLE_MORE_ICON){
            mMoreIconView.setVisibility(View.VISIBLE);
            mItemValueView.setVisibility(View.GONE);
            if(moreIcon!=null)
                mMoreIconView.setImageDrawable(moreIcon);
        }else if(type == Type.TEXT_AND_MORE_ICON){
            mMoreIconView.setVisibility(View.VISIBLE);
            mItemValueView.setVisibility(View.VISIBLE);
            if(moreIcon!=null)
                mMoreIconView.setImageDrawable(moreIcon);
            mItemValueView.setText(itemValue);
        }else if(type == Type.SWITCH_BUTTON){
            mMoreIconView.setVisibility(View.GONE);
            mItemValueView.setVisibility(View.GONE);

            if(stubSwitch!=null)
                stubSwitch.inflate();

            mSwitchButton = findViewById(R.id.switch_button);
            mSwitchButton.setVisibility(View.VISIBLE);
        }
    }


    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
        update();
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
        update();
    }

    public String getItemValue() {
        return itemValue;
    }

    public void setItemValue(String itemValue) {
        this.itemValue = itemValue;
        update();
    }

    public Drawable getMoreIcon() {
        return moreIcon;
    }

    public void setMoreIcon(Drawable moreIcon) {
        this.moreIcon = moreIcon;
        update();
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
        update();
    }

    public boolean isShowTopLine() {
        return showTopLine;
    }

    public void setShowTopLine(boolean showTopLine) {
        this.showTopLine = showTopLine;
        update();
    }

    public boolean isShowBottomLine() {
        return showBottomLine;
    }

    public void setShowBottomLine(boolean showBottomLine) {
        this.showBottomLine = showBottomLine;
        update();
    }

    public int getItemNameColor() {
        return itemNameColor;
    }

    public void setItemNameColor(int itemNameColor) {
        this.itemNameColor = itemNameColor;
        update();
    }

    public int getItemValueColor() {
        return itemValueColor;
    }

    public void setItemValueColor(int itemValueColor) {
        this.itemValueColor = itemValueColor;
        update();
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    @Override
    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
        update();
    }

    public int getLineColor() {
        return lineColor;
    }

    public void setLineColor(int lineColor) {
        this.lineColor = lineColor;
        update();
    }

    public SwitchButton getSwitchButton() {
        return mSwitchButton;
    }
}
