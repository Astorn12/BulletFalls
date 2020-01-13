package com.example.user.bulletfalls.OveralStatisticsAndCollectionsMissions.MissionRewards.RewardKinds;

import android.content.Context;
import android.view.View;

import com.example.user.bulletfalls.Game.Elements.Hero.Hero;
import com.example.user.bulletfalls.Game.Elements.Hero.HeroSpecyfication;

public class HeroReward implements MissionReward {
    HeroSpecyfication heroSpecyfication;

    public HeroReward(HeroSpecyfication heroSpecyfication) {
        this.heroSpecyfication = heroSpecyfication;
    }

    @Override
    public View getIcon(Context context) {
        return new Hero(context,this.heroSpecyfication);
    }

    @Override
    public void rewardUser(Context context) {

    }
}
