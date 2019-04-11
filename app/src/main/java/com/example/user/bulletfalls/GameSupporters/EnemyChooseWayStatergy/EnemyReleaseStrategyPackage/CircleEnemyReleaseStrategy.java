package com.example.user.bulletfalls.GameSupporters.EnemyChooseWayStatergy.EnemyReleaseStrategyPackage;

import android.content.Context;

import com.example.user.bulletfalls.Objects.Enemy;
import com.example.user.bulletfalls.Specyfications.Characters.EnemySpecyfication;

import java.util.List;

public class CircleEnemyReleaseStrategy extends ListEnemyReleaseStrategy {
    int counter;

    public CircleEnemyReleaseStrategy(List<EnemySpecyfication> enemyList, int counter)
    {
        super(enemyList);
        this.counter=counter;
    }

    @Override
    public Enemy releaseChoosenEnemy(Context context) {
        Enemy enemy=new Enemy(context,enemySpecyfications.get(counter%enemySpecyfications.size()));
        counter++;
        return enemy;
    }
}
