package com.example.user.bulletfalls.GameSupporters.EnemyChooseWayStatergy.EnemyReleaseStrategyPackage;

import android.content.Context;

import com.example.user.bulletfalls.Objects.Enemy;
import com.example.user.bulletfalls.Specyfications.Dynamic.Characters.Enemy.EnemySpecyfication;

import java.util.LinkedList;
import java.util.List;

public abstract class ListEnemyReleaseStrategy implements EnemyReleaseStrategy {

   // List<EnemySpecyfication> enemyList;
    protected List<Enemy> enemyList;
    List<EnemySpecyfication> enemySpecyficationSpecyfications;
  /*  public ListEnemyReleaseStrategy(List<EnemySpecyfication> enemyList)
    {
        this.enemyList=enemyList;
    }*/
    public ListEnemyReleaseStrategy(List<EnemySpecyfication> enemySpecyficationSpecyfications)
    {
        this.enemySpecyficationSpecyfications = enemySpecyficationSpecyfications;
        this.enemyList = new LinkedList<>();
    }

    @Override
    public Enemy releaseChoosenEnemy(Context context) {
        return null;
    }
    @Override
    public void putContext(Context context)
    {
        for(EnemySpecyfication es: enemySpecyficationSpecyfications)
        {
            enemyList.add(new Enemy(context,es));
        }
    }
}
