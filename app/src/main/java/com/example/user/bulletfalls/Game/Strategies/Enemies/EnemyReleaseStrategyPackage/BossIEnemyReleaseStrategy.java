package com.example.user.bulletfalls.Game.Strategies.Enemies.EnemyReleaseStrategyPackage;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.user.bulletfalls.Game.Elements.Enemy.Enemy;

public class BossIEnemyReleaseStrategy implements IEnemyReleaseStrategy {

    Enemy boss;

    boolean alreadyReleasedBoss;

    public BossIEnemyReleaseStrategy(Enemy boss)
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

            return clonedBoss;
        }
        return null;
    }



    @Override
    public ViewGroup selfDescribe(Context context) {
        LinearLayout ll= new LinearLayout(context);
        ll.addView(this.boss.clone());
        return ll;
    }
}
