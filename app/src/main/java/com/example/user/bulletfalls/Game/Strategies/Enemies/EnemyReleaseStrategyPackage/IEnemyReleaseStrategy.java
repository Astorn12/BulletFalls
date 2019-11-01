package com.example.user.bulletfalls.Game.Strategies.Enemies.EnemyReleaseStrategyPackage;

import android.content.Context;

import com.example.user.bulletfalls.GlobalUsage.Interfaces.ISelfDescriber;
import com.example.user.bulletfalls.Game.Elements.Enemy.Enemy;

public interface IEnemyReleaseStrategy extends ISelfDescriber {
    Enemy releaseChoosenEnemy(Context context);

}
