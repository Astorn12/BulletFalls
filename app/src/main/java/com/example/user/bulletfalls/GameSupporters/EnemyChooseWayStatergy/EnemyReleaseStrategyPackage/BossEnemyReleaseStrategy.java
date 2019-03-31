package com.example.user.bulletfalls.GameSupporters.EnemyChooseWayStatergy.EnemyReleaseStrategyPackage;

import android.content.Context;

import com.example.user.bulletfalls.ObjectsOfGame.Enemy;

public class BossEnemyReleaseStrategy implements EnemyReleaseStrategy {

    Enemy boss;

    boolean alreadyReleasedBoss;

    public BossEnemyReleaseStrategy(Enemy boss)
    {
        alreadyReleasedBoss=false;
        this.boss=boss;
    }
    @Override
    public Enemy releaseChoosenEnemy(Context context) {
        if(!alreadyReleasedBoss)
        {
            alreadyReleasedBoss=true;
            Enemy clonedBoss=boss.changeContext(context);
            clonedBoss.setContext(context);
            return clonedBoss;
        }
        return null;
    }

    @Override
    public void putContext(Context context) {
    }
}
