package com.example.user.bulletfalls.Strategies.Character.Character.DoToBulletStrategy;

import com.example.user.bulletfalls.Bullet;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("standard")
public class Standard implements DoToBulletStrategy {

    @Override
    public void doToBullet(Bullet bullet) {
        bullet.setPower(0);
    }

    @Override
    public void boostAtribute(int boost) {

    }
}
