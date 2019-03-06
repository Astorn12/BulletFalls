package com.example.user.bulletfalls.GameSupporters;

import com.example.user.bulletfalls.Ability;
import com.example.user.bulletfalls.Enemy;
import com.example.user.bulletfalls.Specyfications.Bullets.BulletSpecyfication;
import com.example.user.bulletfalls.Specyfications.Characters.EnemySpecyfication;

import org.apache.commons.lang3.tuple.MutablePair;

import java.util.LinkedList;
import java.util.List;

public class Medium {

    List<EnemySpecyfication> deathEnemyList;
    List<EnemySpecyfication> pushedEnemys;
    int gameDuration;
    List<MutablePair<Ability, Integer>> usedAbilities;
    List<MutablePair<BulletSpecyfication,Integer>> heroBullets;
    List<MutablePair<BulletSpecyfication,Integer>> enemyBullets;


    public Medium()
    {
        this.deathEnemyList= new LinkedList<>();
        this.pushedEnemys= new LinkedList<>();
        this.heroBullets= new LinkedList<>();
        this.enemyBullets= new LinkedList<>();
    }

    public void startTimer()
    {
        this.gameDuration= 
    }

    /**Do zaprogramowania*/
    // lista zdobytych obietów i obiekt item
    //lista herosupporters stworzyć herosupporter



}
