package com.example.user.bulletfalls.GameSupporters.MediumTasks;

import com.example.user.bulletfalls.Specyfications.Dynamic.Bullets.BulletSpecyfication;
import com.example.user.bulletfalls.Specyfications.Dynamic.Characters.Enemy.EnemySpecyfication;

public class Hitt {
    EnemySpecyfication enemySpecyficationSpecyfication;
    BulletSpecyfication bulletSpecyfication;
    int damage;

    public Hitt(EnemySpecyfication enemySpecyficationSpecyfication, BulletSpecyfication bulletSpecyfication, int damage) {
        this.enemySpecyficationSpecyfication = enemySpecyficationSpecyfication;
        this.bulletSpecyfication = bulletSpecyfication;
        damage = damage;
    }

    public EnemySpecyfication getEnemySpecyficationSpecyfication() {
        return enemySpecyficationSpecyfication;
    }

    public BulletSpecyfication getBulletSpecyfication() {
        return bulletSpecyfication;
    }

    public int getDemage() {
        return damage;
    }
}
