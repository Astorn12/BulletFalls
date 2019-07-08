package com.example.user.bulletfalls.Objects;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.bulletfalls.Sets.BulletSet;
import com.example.user.bulletfalls.Supporters.OnSwipeTouchListener;
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
        Bullet bullet =BulletSet.getBullet(name);

        this.bulletView =(ImageView)this.findViewById(R.id.futurebullet);
        bulletName=(TextView)this.findViewById(R.id.bulletname);
        this.bulletView.setImageResource(bullet.getImageResources());
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
