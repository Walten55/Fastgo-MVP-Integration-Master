
package me.walten.fastgo.progress;

/**
 * If a view implements this interface passed to the HUD as a custom view, its progress
 * can be updated by calling setMax() and setProgress() on the HUD.
 * This interface only provides convenience, how progress work depends on the view implementation.
 */
public interface Determinate {
    void setMax(int max);
    void setProgress(int progress);
}
