package com.example.user.bulletfalls.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.user.bulletfalls.R;

import java.util.Timer;
import java.util.TimerTask;

public class NotReadyYet extends AppCompatActivity {
    ImageView imageView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_ready_yet);

        Intent intent= getIntent();
        int  id=intent.getIntExtra("id",0);
        String title= intent.getStringExtra("title");
        imageView=(ImageView) findViewById(R.id.futureimage);
        textView=(TextView) findViewById(R.id.title);
        textView.setText(title);
        Glide.with(this)
                .load(id)
                .into(imageView);

        startRotateGear();

    }
    private void startRotateGear() {
        final ImageView gear= (ImageView) findViewById(R.id.gear);
        Timer timer= new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
               rotateImage(3,gear);
            }
        }, 0, 30);
    }
    public void rotateImage(final float degree, final ImageView imageView)
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                imageView.setRotation((imageView.getRotation()+degree)%360);

            }
        });

    }
}
