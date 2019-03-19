package com.example.user.bulletfalls.GameSupporters.MediumTasks;

import com.example.user.bulletfalls.Specyfications.Bullets.BulletSpecyfication;
import com.example.user.bulletfalls.Specyfications.Characters.EnemySpecyfication;

public class Hit {
    EnemySpecyfication enemySpecyfication;
    BulletSpecyfication bulletSpecyfication;
    int Demage;

    public Hit(EnemySpecyfication enemySpecyfication, BulletSpecyfication bulletSpecyfication, int demage) {
        this.enemySpecyfication = enemySpecyfication;
        this.bulletSpecyfication = bulletSpecyfication;
        Demage = demage;
    }

    public EnemySpecyfication getEnemySpecyfication() {
        return enemySpecyfication;
    }

    public BulletSpecyfication getBulletSpecyfication() {
        return bulletSpecyfication;
    }

    public int getDemage() {
        return Demage;
    }
}
