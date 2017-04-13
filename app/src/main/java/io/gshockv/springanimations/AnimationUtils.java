package io.gshockv.springanimations;

import android.support.animation.DynamicAnimation;
import android.support.animation.SpringAnimation;
import android.support.animation.SpringForce;
import android.view.View;

public final class AnimationUtils {

    public static SpringAnimation createSpringAnimation(
            View view,
            DynamicAnimation.ViewProperty property,
            float finalPosition,
            float stiffness,
            float dampingRatio) {

        final SpringAnimation animation = new SpringAnimation(view, property);
        final SpringForce spring = new SpringForce(finalPosition);
        spring.setStiffness(stiffness);
        spring.setDampingRatio(dampingRatio);
        animation.setSpring(spring);
        return animation;
    }

    private AnimationUtils() {
    }
}
