package com.example.user.bulletfalls.ObjectsOfGame;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.bulletfalls.Database.JsonDatabases.BulletSet;
import com.example.user.bulletfalls.KlasyPomocnicze.OnSwipeTouchListener;
import com.example.user.bulletfalls.R;

public class BulletProfile extends AppCompatActivity {
    ImageView bulletView;
    TextView bulletName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bullet_profile);

        Intent intent= getIntent();
        String  name=intent.getStringExtra("name");
        Bullet bullet=BulletSet.getBullet(name);

        bulletView=(ImageView)this.findViewById(R.id.futurebullet);
        bulletName=(TextView)this.findViewById(R.id.bulletname);
        bulletView.setImageResource(bullet.getImageResources());
        bulletName.setText(name);
        LinearLayout ll= (LinearLayout) this.findViewById(R.id.bulletScreen);
        final Activity home=this;
        ll.setOnTouchListener(new OnSwipeTouchListener(this)
        {
            @Override
            public void onSwipeLeft() {
                home.finish();
            }

        });

    }
}
