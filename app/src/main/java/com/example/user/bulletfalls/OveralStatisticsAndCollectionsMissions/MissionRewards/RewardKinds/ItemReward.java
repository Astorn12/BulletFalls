package com.example.user.bulletfalls.OveralStatisticsAndCollectionsMissions.MissionRewards.RewardKinds;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.bulletfalls.Profile.Currency;
import com.example.user.bulletfalls.View.IconPresenter;

public class ItemReward implements MissionReward {

     Currency currency;
     int amount;

    public ItemReward(Currency currency, int amount) {
        this.currency = currency;
        this.amount = amount;
    }

    @Override
    public View getIcon(Context context) {


        IconPresenter iconPresenter= new IconPresenter();

        return iconPresenter.presentIcon(context,currency.getResource(),amount+"");
        /*LinearLayout itemView= new LinearLayout(context);
        itemView.setOrientation(LinearLayout.HORIZONTAL);

        TextView amountView= new TextView(context);
        amountView.setText(this.amount+"");
        amountView.setGravity(Gravity.CENTER);

        ImageView imageView= new ImageView(context);
        imageView.setImageResource(this.currency.getResource());

        itemView.addView(amountView);
        itemView.addView(imageView);

        return itemView;*/
    }

    @Override
    public void rewardUser(Context context) {

    }
}
