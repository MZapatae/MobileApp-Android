package cl.mzapatae.mobileApp.helpers.transitions;

import android.animation.Animator;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.transition.Transition;
import android.transition.TransitionValues;
import android.view.View;
import android.view.ViewGroup;

@TargetApi(21)
class ChangeColorTransition extends Transition {

    private static final String PROPNAME_BACKGROUND = "cl.mzapatae:ChangeColorTransition:background";

    private void captureValues(final TransitionValues transitionValues) {
        View view = transitionValues.view;
        transitionValues.values.put(PROPNAME_BACKGROUND, view.getBackground());
    }

    @Override
    public void captureStartValues(final TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    @Override
    public void captureEndValues(final TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    @SuppressLint("NewApi") @Override
    public Animator createAnimator(final ViewGroup sceneRoot, final TransitionValues startValues, final TransitionValues endValues) {
        if (null == startValues || null == endValues) {
            return null;
        }

        final View view = endValues.view;
        Drawable startBackground = (Drawable) startValues.values.get(PROPNAME_BACKGROUND);
        Drawable endBackground = (Drawable) endValues.values.get(PROPNAME_BACKGROUND);

        if (startBackground instanceof ColorDrawable && endBackground instanceof ColorDrawable) {
            ColorDrawable startColor = (ColorDrawable) startBackground;
            ColorDrawable endColor = (ColorDrawable) endBackground;

            if (startColor.getColor() != endColor.getColor()) {
                ValueAnimator animator = ValueAnimator.ofObject(new ArgbEvaluator(), startColor.getColor(), endColor.getColor());
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        Object value = animation.getAnimatedValue();
                        if (null != value) {
                            view.setBackgroundColor((Integer) value);
                        }
                    }
                });
                return animator;
            }
        }
        return null;
    }
}