package com.example.user.bulletfalls.Specyfications.Characters;

import com.example.user.bulletfalls.Objects.Enemy;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("enemyspecyfication")
public class EnemySpecyfication extends CharacterSpecyfication {

    int killValue;

    public EnemySpecyfication(Enemy enemy) {
        super(enemy);

        this.killValue=enemy.getKillValue();
    }
    private EnemySpecyfication() { }

    public int getKillValue() {
        return killValue;
    }

    public void setKillValue(int killValue) {
        this.killValue = killValue;
    }
}
