
package me.walten.fastgo.progress;

/**
 * If a view implements this interface passed to the HUD as a custom view, its animation
 * speed can be change by calling setAnimationSpeed() on the HUD.
 * This interface only provides convenience, how animation speed work depends on the view implementation.
 */
public interface Indeterminate {
    void setAnimationSpeed(float scale);
}
