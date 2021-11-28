package com.yinyang;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button animateBtn, stopBtn;
    private ImageView yinyang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        animateBtn = findViewById(R.id.animate_btn);
        stopBtn = findViewById(R.id.stop_btn);
        yinyang = findViewById(R.id.yinyang);
        animateBtn.setOnClickListener(this);
        stopBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.animate_btn:
                Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.myanim);

                yinyang.startAnimation(myAnim);
                break;
            case R.id.stop_btn:
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }
    }
}