package com.example.user.bulletfalls.OveralStatisticsAndCollectionsMissions.MissionRewards.RewardKinds;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.OrientationEventListener;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.bulletfalls.R;

public class MoneyReward implements MissionReward{
    int amount;

    public MoneyReward(int amount) {
        this.amount = amount;
    }


    @Override
    public View getIcon(Context context) {

        LinearLayout main= new LinearLayout(context);
        main.setOrientation(LinearLayout.HORIZONTAL);

        TextView amountView= new TextView(context);
        amountView.setText(this.amount+"");

        ImageView coin= new ImageView(context);
        coin.setImageResource(R.drawable.mysterycoin);


        main.addView(amountView);
        main.addView(coin);
        return main;
    }

    @Override
    public void rewardUser(Context context) {

    }
}
