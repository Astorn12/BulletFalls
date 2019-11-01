package com.example.user.bulletfalls.Game.Management;

import com.example.user.bulletfalls.Game.Elements.Bullet.Specyfication.BulletSpecyfication;

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
