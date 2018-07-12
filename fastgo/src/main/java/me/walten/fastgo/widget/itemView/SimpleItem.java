package me.walten.fastgo.widget.itemView;

/*
 * -----------------------------------------------------------------
 * Copyright by 2018 Walten, All rights reserved.
 * -----------------------------------------------------------------
 * desc:
 * -----------------------------------------------------------------
 * 2018/7/6 : Create SimpleItem.java (Walten);
 * -----------------------------------------------------------------
 */
public class SimpleItem{

    private int icon = 0;

    private int moreIcon = 0;

    private String itemName = "";

    private String itemValue = "";

    private boolean showTopLine;

    private boolean showBottomLine;

    private int lineColor = 0;

    private int backgroundColor = 0;

    private SimpleItem(){

    }

    public int getIcon() {
        return icon;
    }

    public int getMoreIcon() {
        return moreIcon;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemValue() {
        return itemValue;
    }

    public boolean isShowTopLine() {
        return showTopLine;
    }

    public boolean isShowBottomLine() {
        return showBottomLine;
    }

    public int getLineColor() {
        return lineColor;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }


    public void setIcon(int icon) {
        this.icon = icon;
    }

    public void setMoreIcon(int moreIcon) {
        this.moreIcon = moreIcon;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemValue(String itemValue) {
        this.itemValue = itemValue;
    }

    public void setShowTopLine(boolean showTopLine) {
        this.showTopLine = showTopLine;
    }

    public void setShowBottomLine(boolean showBottomLine) {
        this.showBottomLine = showBottomLine;
    }

    public void setLineColor(int lineColor) {
        this.lineColor = lineColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder {

        private SimpleItem target;

        private Builder() {
            target = new SimpleItem();
        }

        public Builder setIcon(int icon) {
            target.icon = icon;
            return this;
        }

        public Builder setMoreIcon(int moreIcon) {
            target.moreIcon = moreIcon;
            return this;
        }

        public Builder setItemName(String itemName) {
            target.itemName = itemName;
            return this;
        }

        public Builder setItemValue(String itemValue) {
            target.itemValue = itemValue;
            return this;
        }

        public Builder setShowTopLine(boolean showTopLine) {
            target.showTopLine = showTopLine;
            return this;
        }

        public Builder setShowBottomLine(boolean showBottomLine) {
            target.showBottomLine = showBottomLine;
            return this;
        }

        public Builder setLineColor(int lineColor) {
            target.lineColor = lineColor;
            return this;
        }

        public Builder setBackgroundColor(int backgroundColor) {
            target.backgroundColor = backgroundColor;
            return this;
        }

        public SimpleItem build(){
            return target;
        }

    }
}
