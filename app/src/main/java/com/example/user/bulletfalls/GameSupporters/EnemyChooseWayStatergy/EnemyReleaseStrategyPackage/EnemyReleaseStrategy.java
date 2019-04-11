package com.example.user.bulletfalls.GameSupporters.EnemyChooseWayStatergy.EnemyReleaseStrategyPackage;

import android.content.Context;

import com.example.user.bulletfalls.Objects.Enemy;

public interface EnemyReleaseStrategy {
    Enemy releaseChoosenEnemy(Context context);
    void putContext(Context context);
}
