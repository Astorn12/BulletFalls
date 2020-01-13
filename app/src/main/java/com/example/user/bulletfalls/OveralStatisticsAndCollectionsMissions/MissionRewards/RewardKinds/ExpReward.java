package com.example.user.bulletfalls.OveralStatisticsAndCollectionsMissions.MissionRewards.RewardKinds;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

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
