package com.example.du_an_1_nhom_7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img = findViewById(R.id.img);
        startquay(1000);
        new CountDownTimer(3000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(MainActivity.this, Acticity_Dang_Nhap.class);
                startActivity(intent);
            }
        }.start();
    }
    private void startquay(long time){
        Runnable run=new Runnable() {
            @Override
            public void run() {

                img.animate()
                        .rotationBy(360)
                        .withEndAction(this)
                        .setDuration(time)
                        .setInterpolator(new LinearInterpolator())
                        .start();
            }
        };
        img.animate().rotationBy(360)
                .withEndAction(run)
                .setDuration(500)
                .setInterpolator(new LinearInterpolator())
                .start();
    }
}