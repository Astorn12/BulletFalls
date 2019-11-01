package com.example.user.bulletfalls.Game.Strategies.Enemies.EnemyReleaseStrategyPackage;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.user.bulletfalls.Game.Elements.Enemy.Enemy;

public class BossWithSupportersReleaseStrategyI extends BossIEnemyReleaseStrategy {
   ListIEnemyReleaseStrategy listRelease;
    public BossWithSupportersReleaseStrategyI(Enemy boss,ListIEnemyReleaseStrategy listRelease) {
        super(boss);
        this.listRelease=listRelease;
    }

    @Override
    public Enemy releaseChoosenEnemy(Context context) {
        if(!alreadyReleasedBoss)
        {
            alreadyReleasedBoss=true;
            Enemy clonedBoss=boss.changeContext(context);
            //clonedBoss.setContext(context);
            return clonedBoss;
        }
        else
        {
            return this.listRelease.releaseChoosenEnemy(context);
        }
    }


    @Override
    public ViewGroup selfDescribe(Context context) {
        LinearLayout ll= new LinearLayout(context);
        ll.addView(this.boss);
        ll.addView(this.listRelease.selfDescribe(context));
        return ll;
    }
}
