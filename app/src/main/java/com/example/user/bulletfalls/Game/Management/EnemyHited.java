package com.example.user.bulletfalls.Game.Management;

import com.example.user.bulletfalls.Game.Elements.Bullet.Specyfication.BulletSpecyfication;

public class EnemyHited extends Hit{
    BulletSpecyfication bulletSpecyfication;

    public EnemyHited(long time, BulletSpecyfication bulletSpecyfication, int damage) {
        super(time,damage);
        this.bulletSpecyfication = bulletSpecyfication;
        this.damage = damage;
    }

    public BulletSpecyfication getBulletSpecyfication() {
        return bulletSpecyfication;
    }

    public void setBulletSpecyfication(BulletSpecyfication bulletSpecyfication) {
        this.bulletSpecyfication = bulletSpecyfication;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
