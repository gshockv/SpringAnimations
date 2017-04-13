package io.gshockv.springanimations;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openPositionAnimation(View anchor) {
        startActivity(new Intent(this, PositionAnimationActivity.class));
    }

    public void openRotationAnimation(View anchor) {
        startActivity(new Intent(this, RotationAnimationActivity.class));
    }

    public void openScaleAnimation(View anchor) {
        startActivity(new Intent(this, ScaleAnimationActivity.class));
    }
}
