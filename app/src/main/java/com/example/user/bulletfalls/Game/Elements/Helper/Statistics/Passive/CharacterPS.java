package com.example.user.bulletfalls.Game.Elements.Helper.Statistics.Passive;

import com.example.user.bulletfalls.Game.Elements.Bullet.Specyfication.BulletSpecyfication;

public class CharacterPS extends DynamicPS {
    int life;
    int shootingSpeed;
    BulletSpecyfication bulletSpecyfication;

    public CharacterPS(int speed, int life, int shootingSpeed, BulletSpecyfication bulletSpecyfication) {
        super(speed);
        this.life = life;
        this.shootingSpeed = shootingSpeed;
        this.bulletSpecyfication = bulletSpecyfication;
    }



    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getShootingSpeed() {
        return shootingSpeed;
    }

    public void setShootingSpeed(int shootingSpeed) {
        this.shootingSpeed = shootingSpeed;
    }

    public BulletSpecyfication getBulletSpecyfication() {
        return bulletSpecyfication;
    }

    public void setBulletSpecyfication(BulletSpecyfication bulletSpecyfication) {
        this.bulletSpecyfication = bulletSpecyfication;
    }
}
