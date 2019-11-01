package com.example.user.bulletfalls.Game.Management;

import com.example.user.bulletfalls.Game.Elements.Bullet.Specyfication.BulletSpecyfication;
import com.example.user.bulletfalls.Game.Elements.Enemy.EnemySpecyfication;

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
