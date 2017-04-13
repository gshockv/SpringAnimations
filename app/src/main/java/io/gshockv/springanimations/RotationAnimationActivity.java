package io.gshockv.springanimations;

import android.os.Bundle;
import android.support.animation.SpringAnimation;
import android.support.animation.SpringForce;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class RotationAnimationActivity extends BaseToolbarActivity {

    private static final float INITIAL_ROTATION = 0f;
    private static final float STIFFNESS = SpringForce.STIFFNESS_MEDIUM;
    private static final float DAMPING_RATIO = SpringForce.DAMPING_RATIO_HIGH_BOUNCY;

    private SpringAnimation rotationAnimation;

    private double prevRotation = 0f;
    private double currentRotation = 0f;

    private ImageView imageViewRotation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotation);

        imageViewRotation = (ImageView) findViewById(R.id.img_circle);

        rotationAnimation = AnimationUtils.createSpringAnimation(
                imageViewRotation,
                SpringAnimation.ROTATION,
                INITIAL_ROTATION,
                STIFFNESS,
                DAMPING_RATIO
        );

        imageViewRotation.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                double centerX = v.getWidth() / 2.0;
                double centerY = v.getHeight() / 2.0;
                double x = event.getX();
                double y = event.getY();

                if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
                    rotationAnimation.cancel();
                    updateCurrentLocation(v, x, y, centerX, centerY);
                } else if (event.getActionMasked() == MotionEvent.ACTION_MOVE) {
                    prevRotation = currentRotation;
                    updateCurrentLocation(v, x, y, centerX, centerY);
                    float angle = (float) (currentRotation - prevRotation);
                    v.setRotation(v.getRotation() + angle);
                } else if (event.getActionMasked() == MotionEvent.ACTION_UP) {
                    rotationAnimation.start();
                    updateCurrentLocation(v, x, y, centerX, centerY);
                }

                return true;
            }
        });
    }

    private void updateCurrentLocation(View v, double x, double y, double centerX, double centerY) {
        currentRotation = v.getRotation() + Math.toDegrees(Math.atan2(x - centerX, centerY - y));
    }
}
