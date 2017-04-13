package io.gshockv.springanimations;

import android.os.Bundle;
import android.support.animation.SpringAnimation;
import android.support.animation.SpringForce;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ImageView;

public class ScaleAnimationActivity extends BaseToolbarActivity {

    private static final float INITIAL_SCALE = 1f;
    private static final float STIFFNESS = SpringForce.STIFFNESS_MEDIUM;
    private static final float DAMPING_RATIO = SpringForce.DAMPING_RATIO_HIGH_BOUNCY;

    private SpringAnimation scaleXAnimation;
    private SpringAnimation scaleYAnimation;
    private ScaleGestureDetector scaleGestureDetector;
    private float scaleFactor = 1f;

    private ImageView imageViewAndroid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scale);

        imageViewAndroid = (ImageView) findViewById(R.id.img_android);

        scaleXAnimation = AnimationUtils.createSpringAnimation(
                imageViewAndroid,
                SpringAnimation.SCALE_X,
                INITIAL_SCALE,
                STIFFNESS,
                DAMPING_RATIO
        );
        scaleYAnimation = AnimationUtils.createSpringAnimation(
                imageViewAndroid,
                SpringAnimation.SCALE_Y,
                INITIAL_SCALE,
                STIFFNESS,
                DAMPING_RATIO
        );

        setupPinchToZoom();

        imageViewAndroid.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    scaleXAnimation.start();
                    scaleYAnimation.start();
                } else {
                    scaleXAnimation.cancel();
                    scaleYAnimation.cancel();

                    scaleGestureDetector.onTouchEvent(event);
                }
                return true;
            }
        });
    }

    private void setupPinchToZoom() {
        scaleGestureDetector = new ScaleGestureDetector(this, new ScaleGestureDetector.SimpleOnScaleGestureListener() {
            @Override
            public boolean onScale(ScaleGestureDetector detector) {
                scaleFactor *= detector.getScaleFactor();
                imageViewAndroid.setScaleX(imageViewAndroid.getScaleX() * scaleFactor);
                imageViewAndroid.setScaleY(imageViewAndroid.getScaleY() * scaleFactor);
                return true;
            }
        });
    }
}
