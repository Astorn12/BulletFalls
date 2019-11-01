package com.example.user.bulletfalls.Game.Strategies.Enemies.EnemyReleaseStrategyPackage;

import android.content.Context;

import com.example.user.bulletfalls.Game.Elements.Enemy.Enemy;
import com.example.user.bulletfalls.Game.Elements.Enemy.EnemySpecyfication;

import java.util.List;
import java.util.Random;

public class RandomIEnemyReleaseStrategy extends ListIEnemyReleaseStrategy {

    Random random;

    public RandomIEnemyReleaseStrategy(List<EnemySpecyfication> enemySpecyficationList) {
        super(enemySpecyficationList);
        random= new Random();
    }

    @Override
    public Enemy releaseChoosenEnemy(Context context) {
        Enemy enemy = new Enemy(context, enemySpecyficationSpecyfications.get(random.nextInt(enemySpecyficationSpecyfications.size() - 1)));
        return enemy;
    }
}
