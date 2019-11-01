package com.example.user.bulletfalls.Profile;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.user.bulletfalls.GlobalUsage.Supporters.GuiSupporters.BorderSetter;
import com.example.user.bulletfalls.R;

public class LevelBar {

    LinearLayout bar;
    TextView level;
    ImageView laurel;
    FrameLayout frame;
    Context context;
    FrameLayout levelFrame;

    public LevelBar(Context context) {
        this.frame= new FrameLayout(context);
        this.bar= new LinearLayout(context);
        this.level= new TextView(context);
        this.laurel= new ImageView(context);
        this.context=context;
        Glide.with(context).load(R.drawable.lauerl).into(this.laurel);
        this.bar.setOrientation(LinearLayout.HORIZONTAL);
        this.levelFrame= new FrameLayout(context);
        frame.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
        frame.post(new Runnable() {
            @Override
            public void run() {

                prepareBelt();

            }
        });
    }

    private void prepareBelt()
    {

        this.bar.setGravity(Gravity.CENTER);
        FrameLayout.LayoutParams params= new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        params.setMargins(20,25,20,25);
        //this.bar.setLayoutParams(params);
        this.levelFrame.setLayoutParams(params);

        this.laurel.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
        this.level.setGravity(Gravity.CENTER);
        this.level.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
        this.level.setTextColor(Color.WHITE);
        this.level.setTextSize(20);
        final RelativeLayout v1= new RelativeLayout(context);
        final RelativeLayout v2= new RelativeLayout(context);

        this.bar.addView(v1);
        v1.setBackgroundColor(Color.rgb(235,122,50));
        this.bar.addView(v2);

        BorderSetter borderSetter= new BorderSetter(2,Color.BLACK);
        BorderSetter borderSetter2= new BorderSetter(3,Color.rgb(230,203,72));

        borderSetter2.setBorder(this.bar);
        borderSetter.setBorder(this.bar);
        v2.setBackgroundColor(Color.rgb(238,234,235));

        frame.getLayoutParams().width=400;

        int w=frame.getLayoutParams().width;
        UserProfile userProfile= new UserProfile(context);
        Level levell=userProfile.getLevel();
        this.level.setText(levell.number+"");
        int required=levell.requiredXp;
        int exp=userProfile.getExp();
        //exp=5; //linia testowa
        final int tmp=(int) (w*((float)exp/(float)(exp+required)));
        v1.setLayoutParams(new LinearLayout.LayoutParams(tmp,LinearLayout.LayoutParams.MATCH_PARENT));
        v2.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT));
        TextView possesExp= new TextView(context);
        TextView requiredExp= new TextView(context);
        possesExp.setText(exp+"");

        requiredExp.setText(levell.getRequiredXp()+"");
        FrameLayout.LayoutParams paramsLeft= new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        paramsLeft.gravity=Gravity.LEFT;

        FrameLayout.LayoutParams paramsRight= new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        paramsRight.gravity=Gravity.RIGHT;


        frame.addView(levelFrame);
        levelFrame.addView(bar);
        levelFrame.addView(possesExp,paramsLeft);
        levelFrame.addView(requiredExp,paramsRight);
        frame.addView(laurel);
        frame.addView(level);


    }

    public FrameLayout get()
    {
       return this.frame;
    }


}
