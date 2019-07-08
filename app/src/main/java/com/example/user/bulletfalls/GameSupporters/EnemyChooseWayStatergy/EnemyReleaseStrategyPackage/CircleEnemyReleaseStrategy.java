package com.example.user.bulletfalls.GameSupporters.EnemyChooseWayStatergy.EnemyReleaseStrategyPackage;

import android.content.Context;

import com.example.user.bulletfalls.Objects.Enemy;
import com.example.user.bulletfalls.Specyfications.Dynamic.Characters.Enemy.EnemySpecyfication;

import java.util.List;

public class CircleEnemyReleaseStrategy extends ListEnemyReleaseStrategy {
    int counter;

    public CircleEnemyReleaseStrategy(List<EnemySpecyfication> enemySpecyficationList, int counter)
    {
        super(enemySpecyficationList);
        this.counter=counter;
    }

    @Override
    public Enemy releaseChoosenEnemy(Context context) {
        Enemy enemy =new Enemy(context, enemySpecyficationSpecyfications.get(counter% enemySpecyficationSpecyfications.size()));
        counter++;
        return enemy;
    }
}
