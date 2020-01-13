package com.example.user.bulletfalls.OveralStatisticsAndCollectionsMissions.MissionRewards.RewardKinds;

import android.content.Context;
import android.view.View;

import com.example.user.bulletfalls.Game.Elements.Bullet.Bullet;
import com.example.user.bulletfalls.Game.Elements.Bullet.Specyfication.BulletSpecyfication;
import com.example.user.bulletfalls.Storage.Sets.BulletSet;

import org.apache.commons.lang3.NotImplementedException;

public class BulletReward implements MissionReward {
    BulletSpecyfication bulletSpecyfication;

    public BulletReward(BulletSpecyfication bulletSpecyfication) {
        this.bulletSpecyfication = bulletSpecyfication;
    }

    @Override
    public View getIcon(Context context) {
        return new Bullet(context, this.bulletSpecyfication);
    }

    @Override
    public void rewardUser(Context context) {
        throw new NotImplementedException("Jeszcze nie zaimplementowanano BuleltReward");
    }

}
