package com.example.user.bulletfalls.Specyfications.Characters;

import com.example.user.bulletfalls.Enemy;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("enemy")
public class EnemySpecyfication extends CharacterSpecyfication {

    int killValue;

    public EnemySpecyfication(Enemy enemy) {
        super(enemy);

        this.killValue=enemy.getKillValue();
    }
    public EnemySpecyfication()
    {


    }

    public int getKillValue() {
        return killValue;
    }

    public void setKillValue(int killValue) {
        this.killValue = killValue;
    }
}
