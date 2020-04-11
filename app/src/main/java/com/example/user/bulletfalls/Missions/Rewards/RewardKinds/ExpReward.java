package com.example.user.bulletfalls.Missions.Rewards.RewardKinds;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

public class ExpReward implements MissionReward {

    int amoutnt;

    public ExpReward(int amoutnt) {
        this.amoutnt = amoutnt;
    }

    @Override
    public View getIcon(Context context) {

        TextView textView= new TextView(context);
        textView.setText(this.amoutnt+" exp");
        return textView;
    }

    @Override
    public void rewardUser(Context context) {

    }
}
