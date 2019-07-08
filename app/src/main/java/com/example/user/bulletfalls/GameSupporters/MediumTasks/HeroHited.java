package com.example.user.bulletfalls.GameSupporters.MediumTasks;

import com.example.user.bulletfalls.Specyfications.Dynamic.Bullets.BulletSpecyfication;

public class HeroHited extends  Hit{
    BulletSpecyfication bulletSpecyfication;
    //EnemySpecyfication enemySpecyficationSpecyfication;

    public HeroHited(long time, int damage, BulletSpecyfication bulletSpecyfication) {
        super(time, damage);
        this.bulletSpecyfication = bulletSpecyfication;
     //   this.enemySpecyficationSpecyfication = enemySpecyficationSpecyfication;
    }

    public BulletSpecyfication getBulletSpecyfication() {
        return bulletSpecyfication;
    }

    public void setBulletSpecyfication(BulletSpecyfication bulletSpecyfication) {
        this.bulletSpecyfication = bulletSpecyfication;
    }
}
