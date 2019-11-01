package com.example.user.bulletfalls.Game.Management;

import com.example.user.bulletfalls.Game.Elements.Bullet.Specyfication.BulletSpecyfication;
import com.example.user.bulletfalls.Game.Elements.Enemy.EnemySpecyfication;


public class EnemyShot {
    EnemySpecyfication enemySpecyficationSpecyfication;
    BulletSpecyfication bulletSpecyfication;

    public EnemyShot(EnemySpecyfication enemySpecyficationSpecyfication, BulletSpecyfication bulletSpecyfication) {
        this.enemySpecyficationSpecyfication = enemySpecyficationSpecyfication;
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
        if(this.enemySpecyficationSpecyfication.equals(enemyShot.getEnemySpecyficationSpecyfication()))
        {
            return true;
        }
        else return false;
    }
    public boolean equals(EnemyShot enemyShot)
    {
        if(enemyShot.bulletSpecyfication.getName().equals(this.bulletSpecyfication.getName())&&enemyShot.getEnemySpecyficationSpecyfication().equals(this.enemySpecyficationSpecyfication)) {
            return true;
        }
        else{
            return false;
        }
    }

    public EnemySpecyfication getEnemySpecyficationSpecyfication() {
        return enemySpecyficationSpecyfication;
    }

    public BulletSpecyfication getBulletSpecyfication() {
        return bulletSpecyfication;
    }


}
