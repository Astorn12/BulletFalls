package com.example.user.bulletfalls.GameSupporters.EnemyChooseWayStatergy.EnemyReleaseStrategyPackage;

import com.example.user.bulletfalls.Enemy;

import java.util.List;

public abstract class BasicEnemyReleaseStrategy implements EnemyReleaseStrategy {

    List<Enemy> enemyList;
    public BasicEnemyReleaseStrategy(List<Enemy> enemyList)
    {
        this.enemyList=enemyList;
    }

    @Override
    public Enemy releaseChoosenEnemy() {
        return null;
    }
}
