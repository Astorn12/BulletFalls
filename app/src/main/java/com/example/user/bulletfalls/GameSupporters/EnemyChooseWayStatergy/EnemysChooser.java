package com.example.user.bulletfalls.GameSupporters.EnemyChooseWayStatergy;

import android.content.Context;

import com.example.user.bulletfalls.ObjectsOfGame.Enemy;
import com.example.user.bulletfalls.GameSupporters.EnemyChooseWayStatergy.EnemyReleaseStrategyPackage.EnemyReleaseStrategy;
import com.example.user.bulletfalls.GameSupporters.EnemyChooseWayStatergy.TimeReleaseStrategyPackage.TimeReleaseStrategy;

public class EnemysChooser {
    EnemyReleaseStrategy enemyReleaseStrategy;
    TimeReleaseStrategy timeReleaseStrategy;
    public EnemysChooser(EnemyReleaseStrategy enemyReleaseStrategy, TimeReleaseStrategy timeReleaseStrategy)
    {
        this.enemyReleaseStrategy=enemyReleaseStrategy;
        this.timeReleaseStrategy=timeReleaseStrategy;
    }

    public void Start()
    {
        timeReleaseStrategy.start();
    }

    public Enemy getEnemy(Context context)
    {
        if(timeReleaseStrategy.ifRelease())
        {
            return enemyReleaseStrategy.releaseChoosenEnemy(context);
        }
            return null;
    }

    public void putContext(Context context)
    {
        this.enemyReleaseStrategy.putContext(context);
    }


}
