package com.example.user.bulletfalls.Game.Strategies.Requirements;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.bulletfalls.Profile.UserProfile;

public class LevelRequirements implements IGameRequirements {
    int lvl;

    public LevelRequirements(int lvl) {
        this.lvl = lvl;
    }

    @Override
    public boolean canPlay(Context context) {
        UserProfile userProfile=new UserProfile(context);
        if(userProfile.getLevel().getNumber()>this.lvl)
        return true;
        else return false;
    }

    @Override
    public ViewGroup selfDescribe(Context context) {
        LinearLayout ll= new LinearLayout(context);
        ll.setGravity(Gravity.CENTER);
        TextView textView = new TextView(context);
        textView.setText(this.lvl+"lvl");
        textView.setTextSize(28);
        textView.setTextColor(Color.YELLOW);
        ll.addView(textView);
        return ll;
    }
}
