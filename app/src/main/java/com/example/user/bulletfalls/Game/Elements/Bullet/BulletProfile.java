package com.example.user.bulletfalls.Game.Elements.Bullet;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.bulletfalls.Game.Elements.Bullet.Specyfication.BulletSpecyfication;
import com.example.user.bulletfalls.Storage.Sets.BulletSet;
import com.example.user.bulletfalls.GlobalUsage.Supporters.GuiSupporters.SupporterBackground;
import com.example.user.bulletfalls.GlobalUsage.Supporters.OnSwipeTouchListener;
import com.example.user.bulletfalls.R;
import com.example.user.bulletfalls.GlobalUsage.Supporters.TextViewSupporters.SupporterTextView;

import java.util.HashMap;
import java.util.Map;

public class BulletProfile extends AppCompatActivity {
    ImageView bulletView;
    TextView bulletName;
    LinearLayout specyfication;
    LinearLayout doToCharacterLayout;
    BulletSpecyfication bullet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bullet_profile);

        Intent intent= getIntent();
        String  name=intent.getStringExtra("name");
        this.bullet =BulletSet.getInstance().getBullet(name);

        this.bulletView =(ImageView)this.findViewById(R.id.futurebullet);
        bulletName=(TextView)this.findViewById(R.id.bulletname);
        this.specyfication=(LinearLayout)findViewById(R.id.specyfication);
        this.bulletView.setImageResource(bullet.getImage());
        bulletName.setText(name);
        bulletName.setBackgroundColor(bullet.getRarity().getValue());
        LinearLayout ll= (LinearLayout) this.findViewById(R.id.bulletScreen);
        this.doToCharacterLayout=(LinearLayout)findViewById(R.id.dotocharacterstrategy);
        final Activity home=this;
        ll.setOnTouchListener(new OnSwipeTouchListener(this)
        {
            @Override
            public void onSwipeLeft() {
                home.finish();
            }

        });
        setBulletDescription();
        SupporterBackground supporterBackground= new SupporterBackground();
        supporterBackground.setChildViewBackground(this.specyfication);
        SupporterTextView supporterTextView= new SupporterTextView();
        supporterTextView.setTextViewsTextSize(this.specyfication,24);
        bullet.getBulletDoToCharacterStrategy().showOwnDescription(this.doToCharacterLayout);
    }

    private void setBulletDescription()
    {
        Map<String,String> map= new HashMap<String, String>() {{
            put("Power: ", bullet.getPower()+"");
            put("Speed: ",bullet.getSpeed()+"" );
            put("Speed: ",bullet.getSpeed()+"" );

            put("Size: ",bullet.getSpeed()+" napraw mnie" );
            put("Colisions: ",bullet.isCollisionAble()+"" );
            put("Move: ",bullet.getBulletMoveStrategy().describe() );
            put("Colisions ",bullet.isCollisionAble()+"" );

        }};
        showBulletDescription(map);
    }

    private void showBulletDescription(Map<String,String> map)
    {

        for(Map.Entry<String,String> entry : map.entrySet())
        {
            LinearLayout linearLayout= new LinearLayout(this);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);

            TextView textView= new TextView(this);
            textView.setText(entry.getKey());

            TextView textView1= new TextView(this);
            textView1.setText(entry.getValue());

            linearLayout.addView(textView);
            linearLayout.addView(textView1);

            this.specyfication.addView(linearLayout);
        }

    }
}
