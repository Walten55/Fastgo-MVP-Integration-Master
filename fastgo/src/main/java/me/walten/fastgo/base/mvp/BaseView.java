package me.walten.fastgo.base.mvp;

/**
 * View基类
 */
public interface BaseView {
    /**
     * 提示框
     * @param msg
     */
    void showTipDialog(int opsStatus, String msg);

    /**
     * 提示框
     * @param msg
     */
    void showTipDialog(String msg);
    /**
     * 加载框
     * @param msg
     */
    void startWaiting(String msg);

    /**
     * 隐藏加载框
     */
    void stopWaiting();

    /**
     * 吐司
     * @param opsStatus
     * @param msg
     */
    void showToast(int opsStatus, String msg);

    /**
     * 吐司
     * @param msg
     */
    void showToast(String msg);

    void finishView();
}
