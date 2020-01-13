package com.example.user.bulletfalls.OveralStatisticsAndCollectionsMissions.MissionRewards.RewardKinds;

import android.content.Context;
import android.view.View;

import com.example.user.bulletfalls.Game.Elements.Ability.Ability;
import com.example.user.bulletfalls.Game.Elements.Ability.Specyfication.AbilitySpecyfication;

public class AbilityReward implements MissionReward {
    AbilitySpecyfication abilitySpecyfication;

    public AbilityReward(AbilitySpecyfication abilitySpecyfication) {
        this.abilitySpecyfication = abilitySpecyfication;
    }

    @Override
    public View getIcon(Context context) {
        return new Ability(context, this.abilitySpecyfication);
    }

    @Override
    public void rewardUser(Context context) {

    }
}
