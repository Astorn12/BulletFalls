package com.example.user.bulletfalls.Objects;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.bulletfalls.Sets.AbilitySet;
import com.example.user.bulletfalls.Specyfications.AbilitySpecyfication;
import com.example.user.bulletfalls.Supporters.OnSwipeTouchListener;
import com.example.user.bulletfalls.R;

public class AbilityProfile extends AppCompatActivity {
    ImageView abilityView;
    TextView abilityName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ability_profile);

        Intent intent= getIntent();
        String  name=intent.getStringExtra("name");
        AbilitySpecyfication abilitySpecyfication =AbilitySet.getInstance().getAbility(name);

        abilityView=(ImageView)this.findViewById(R.id.futureability);
        abilityName=(TextView)this.findViewById(R.id.abilityname);
        abilityView.setImageResource(abilitySpecyfication.getImageResources());
        abilityName.setText(name);


        LinearLayout ll= (LinearLayout) this.findViewById(R.id.abilityScreen);
        final Activity home=this;
        ll.setOnTouchListener(new OnSwipeTouchListener(this)
        {
            @Override
            public void onSwipeDown() {
                home.finish();
            }

        });
    }
}
