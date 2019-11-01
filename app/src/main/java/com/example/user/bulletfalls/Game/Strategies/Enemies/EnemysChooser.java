package com.example.user.bulletfalls.Game.Strategies.Enemies;

import android.content.Context;
import android.view.ViewGroup;

import com.example.user.bulletfalls.Game.Strategies.Enemies.EnemyReleaseStrategyPackage.IEnemyReleaseStrategy;
import com.example.user.bulletfalls.GlobalUsage.Interfaces.ISelfDescriber;
import com.example.user.bulletfalls.Game.Elements.Enemy.Enemy;
import com.example.user.bulletfalls.Game.Strategies.Enemies.TimeReleaseStrategyPackage.TimeReleaseStrategy;

public class EnemysChooser implements ISelfDescriber {
    IEnemyReleaseStrategy iEnemyReleaseStrategy;
    TimeReleaseStrategy timeReleaseStrategy;
    public EnemysChooser(IEnemyReleaseStrategy iEnemyReleaseStrategy, TimeReleaseStrategy timeReleaseStrategy)
    {
        this.iEnemyReleaseStrategy = iEnemyReleaseStrategy;
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
            return iEnemyReleaseStrategy.releaseChoosenEnemy(context);
        }
            return null;
    }




    @Override
    public ViewGroup selfDescribe(Context context) {
        return this.iEnemyReleaseStrategy.selfDescribe(context);
    }
}
