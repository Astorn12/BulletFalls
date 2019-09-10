package com.example.user.bulletfalls.Objects;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.bulletfalls.Sets.AbilitySet;
import com.example.user.bulletfalls.Specyfications.AbilitySpecyfication;
import com.example.user.bulletfalls.Strategies.Abilities.SummonerPackage.BeastRaisers.Linear;
import com.example.user.bulletfalls.Supporters.GuiSupporters.BorderSetter;
import com.example.user.bulletfalls.Supporters.GuiSupporters.SupporterBackground;
import com.example.user.bulletfalls.Supporters.OnSwipeTouchListener;
import com.example.user.bulletfalls.R;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

public class AbilityProfile extends AppCompatActivity {
    ImageView abilityView;
    TextView abilityName;
    TextView description;

    TextView renewalTime;
    TextView unique;
    LinearLayout timeplusrarity;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ability_profile);

        Intent intent= getIntent();
        String  name=intent.getStringExtra("name");
        AbilitySpecyfication abilitySpecyfication =AbilitySet.getInstance().getAbility(name);

        abilityView=(ImageView)this.findViewById(R.id.futureability);
        abilityName=(TextView)this.findViewById(R.id.abilityname);
        timeplusrarity=(LinearLayout) this.findViewById(R.id.renewalplusrarity);
        abilityView.setImageResource(abilitySpecyfication.getImageResources());
        abilityName.setText(name);

        abilityName.setBackgroundColor(abilitySpecyfication.getRarity().getValue());

        description=(TextView)findViewById(R.id.textView3);
        LinearLayout ll= (LinearLayout) this.findViewById(R.id.abilityScreen);

        description.setText(abilitySpecyfication.getDoToCharacterStrategy().getDescription());
        description.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        SupporterBackground supporterBackground= new SupporterBackground();
        supporterBackground.setTextViewBackground(description);
        abilitySpecyfication.getDoToCharacterStrategy().setAdditionalDescription(ll);

        this.renewalTime=(TextView)findViewById(R.id.renewaltime);
        this.unique=(TextView) findViewById(R.id.unique);
        timeplusrarity.setBackgroundColor(Color.BLACK);
        timeplusrarity.setAlpha(0.5f);


        if(abilitySpecyfication.isUnique()) {
            unique.setText("Unique");
            unique.setTextColor(Color.parseColor("#ffd700"));
        }
        else{
            unique.setText("Not-unique");
        }

        this.renewalTime.setText("Renewal time: "+abilitySpecyfication.getRenewalTime()/1000+"s");


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
