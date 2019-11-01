package com.example.user.bulletfalls.Game.Strategies.Enemies.EnemyReleaseStrategyPackage;

import android.content.Context;

import com.example.user.bulletfalls.Game.Elements.Enemy.Enemy;
import com.example.user.bulletfalls.Game.Elements.Enemy.EnemySpecyfication;

import java.util.List;

public class CircleIEnemyReleaseStrategy extends ListIEnemyReleaseStrategy {
    int counter;

    public CircleIEnemyReleaseStrategy(List<EnemySpecyfication> enemySpecyficationList, int counter)
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
