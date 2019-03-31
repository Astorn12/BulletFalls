package com.example.user.bulletfalls.GameSupporters.MediumTasks;

import com.example.user.bulletfalls.Specyfications.Bullets.BulletSpecyfication;
import com.example.user.bulletfalls.Specyfications.Characters.EnemySpecyfication;

public class EnemyShot {
    EnemySpecyfication enemySpecyfication;
    BulletSpecyfication bulletSpecyfication;

    public EnemyShot(EnemySpecyfication enemySpecyfication, BulletSpecyfication bulletSpecyfication) {
        this.enemySpecyfication = enemySpecyfication;
        this.bulletSpecyfication = bulletSpecyfication;

    }

    public boolean equalBullets(EnemyShot enemyShot)
    {
        if(enemyShot.getBulletSpecyfication().getName().equals(this.bulletSpecyfication.getName()))
        {
            return true;
        }
        return false;
    }

    public boolean sameEnemy(EnemyShot enemyShot)
    {
        if(this.enemySpecyfication.equals(enemyShot.getEnemySpecyfication()))
        {
            return true;
        }
        else return false;
    }
    public boolean equals(EnemyShot enemyShot)
    {
        if(enemyShot.bulletSpecyfication.getName().equals(this.bulletSpecyfication.getName())&&enemyShot.getEnemySpecyfication().equals(this.enemySpecyfication)) {
            return true;
        }
        else{
            return false;
        }
    }

    public EnemySpecyfication getEnemySpecyfication() {
        return enemySpecyfication;
    }

    public BulletSpecyfication getBulletSpecyfication() {
        return bulletSpecyfication;
    }


}
