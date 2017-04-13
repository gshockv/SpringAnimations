package io.gshockv.springanimations;

import android.os.Bundle;
import android.support.animation.SpringAnimation;
import android.support.animation.SpringForce;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

public class PositionAnimationActivity extends BaseToolbarActivity {

    private static final float STIFFNESS = SpringForce.STIFFNESS_MEDIUM;
    private static final float DAMPING_RATIO = SpringForce.DAMPING_RATIO_HIGH_BOUNCY;

    private SpringAnimation xAnimation;
    private SpringAnimation yAnimation;

    private float dX = 0f;
    private float dY = 0f;

    private ImageView imageViewAndroid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_position);

        imageViewAndroid = (ImageView) findViewById(R.id.img_android);

        imageViewAndroid.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                xAnimation = AnimationUtils.createSpringAnimation(
                        imageViewAndroid,
                        SpringAnimation.X,
                        imageViewAndroid.getX(),
                        STIFFNESS,
                        DAMPING_RATIO
                );
                yAnimation = AnimationUtils.createSpringAnimation(
                        imageViewAndroid,
                        SpringAnimation.Y,
                        imageViewAndroid.getY(),
                        STIFFNESS,
                        DAMPING_RATIO
                );
            }
        });

        imageViewAndroid.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getActionMasked()) {
                    case MotionEvent.ACTION_DOWN :
                        dX = v.getX() - event.getRawX();
                        dY = v.getY() - event.getRawY();
                        xAnimation.cancel();
                        yAnimation.cancel();
                        break;
                    case MotionEvent.ACTION_MOVE :
                        imageViewAndroid.animate()
                                .x(event.getRawX() + dX)
                                .y(event.getRawY() + dY)
                                .setDuration(0)
                                .start();
                        break;
                    case MotionEvent.ACTION_UP :
                        xAnimation.start();
                        yAnimation.start();
                        break;
                }
                return true;
            }
        });
    }
}
