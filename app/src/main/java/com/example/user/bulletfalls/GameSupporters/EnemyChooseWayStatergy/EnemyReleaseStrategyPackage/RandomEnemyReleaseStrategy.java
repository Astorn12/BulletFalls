package com.example.user.bulletfalls.GameSupporters.EnemyChooseWayStatergy.EnemyReleaseStrategyPackage;

import android.content.Context;

import com.example.user.bulletfalls.Objects.Enemy;
import com.example.user.bulletfalls.Specyfications.Dynamic.Characters.Enemy.EnemySpecyfication;

import java.util.List;
import java.util.Random;

public class RandomEnemyReleaseStrategy extends ListEnemyReleaseStrategy {

    Random random;

    public RandomEnemyReleaseStrategy(List<EnemySpecyfication> enemySpecyficationList) {
        super(enemySpecyficationList);
        random= new Random();
    }

    @Override
    public Enemy releaseChoosenEnemy(Context context) {
        if(this.enemyList !=null && this.enemyList.size()==0) {
            Enemy enemy = new Enemy(context, enemySpecyficationSpecyfications.get(random.nextInt(enemySpecyficationSpecyfications.size() - 1)));
            return enemy;
        }
        else
        {
            return enemyList.get(random.nextInt(enemyList.size() - 1)).clone();
        }

    }
}
