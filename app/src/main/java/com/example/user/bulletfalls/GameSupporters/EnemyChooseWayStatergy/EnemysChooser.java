package com.example.user.bulletfalls.GameSupporters.EnemyChooseWayStatergy;

import com.example.user.bulletfalls.Enemy;
import com.example.user.bulletfalls.GameSupporters.EnemyChooseWayStatergy.EnemyReleaseStrategyPackage.EnemyReleaseStrategy;
import com.example.user.bulletfalls.GameSupporters.EnemyChooseWayStatergy.TimeReleaseStrategyPackage.TimeReleaseStrategy;

public class EnemyChooser {
    EnemyReleaseStrategy enemyReleaseStrategy;
    TimeReleaseStrategy timeReleaseStrategy;
    public EnemyChooser(EnemyReleaseStrategy enemyReleaseStrategy,TimeReleaseStrategy timeReleaseStrategy)
    {
        this.enemyReleaseStrategy=enemyReleaseStrategy;
        this.timeReleaseStrategy=timeReleaseStrategy;
    }

    public void Start()
    {
        enemyReleaseStrategy.start;
        timeReleaseStrategy.start;
    }

    public Enemy getEnemy()
    {


        return null;
    }
}
