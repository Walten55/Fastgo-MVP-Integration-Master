package me.walten.fastgo.base.mvp;

/**
 * 数据View状态基类
 */
public interface DataStatusView extends BaseView {
    /**
     * 数据加载失败
     */
    void statusError();

    /**
     * 空数据
     */
    void statusEmpty();

    /**
     * 数据加载成功
     */
    void statusSuccess();

    /**
     * 非弹框式Loading
     * @param msg
     */
    void statusLoadingInLayout(String msg);
}
