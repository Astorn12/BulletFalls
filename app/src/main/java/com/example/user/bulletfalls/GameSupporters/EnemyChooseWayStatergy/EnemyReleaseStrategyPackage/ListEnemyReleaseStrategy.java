package com.example.user.bulletfalls.GameSupporters.EnemyChooseWayStatergy.EnemyReleaseStrategyPackage;

import android.content.Context;

import com.example.user.bulletfalls.ObjectsOfGame.Enemy;
import com.example.user.bulletfalls.Specyfications.Characters.EnemySpecyfication;

import java.util.LinkedList;
import java.util.List;

public abstract class ListEnemyReleaseStrategy implements EnemyReleaseStrategy {

   // List<Enemy> enemyList;
    protected List<Enemy> enemyList;
    List<EnemySpecyfication> enemySpecyfications;
  /*  public ListEnemyReleaseStrategy(List<Enemy> enemyList)
    {
        this.enemyList=enemyList;
    }*/
    public ListEnemyReleaseStrategy(List<EnemySpecyfication> enemySpecyfications)
    {
        this.enemySpecyfications=enemySpecyfications;
        this.enemyList= new LinkedList<>();
    }

    @Override
    public Enemy releaseChoosenEnemy(Context context) {
        return null;
    }
    @Override
    public void putContext(Context context)
    {
        for(EnemySpecyfication es: enemySpecyfications)
        {
            enemyList.add(new Enemy(context,es));
        }
    }
}
