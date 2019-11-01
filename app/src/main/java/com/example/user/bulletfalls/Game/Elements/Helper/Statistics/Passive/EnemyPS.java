package com.example.user.bulletfalls.Game.Elements.Helper.Statistics.Passive;

import com.example.user.bulletfalls.Game.Elements.Bullet.Specyfication.BulletSpecyfication;

public class EnemyPS extends CharacterPS {
    int killValue;

    public EnemyPS(int speed, int life, int shootingSpeed, BulletSpecyfication bulletSpecyfication, int killValue) {
        super(speed, life, shootingSpeed, bulletSpecyfication);
        this.killValue = killValue;
    }

    public int getKillValue() {
        return killValue;
    }

    public void setKillValue(int killValue) {
        this.killValue = killValue;
    }
}
