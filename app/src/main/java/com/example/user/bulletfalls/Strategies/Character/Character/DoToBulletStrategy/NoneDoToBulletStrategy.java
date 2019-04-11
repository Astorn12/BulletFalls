package com.example.user.bulletfalls.Strategies.Character.Character.DoToBulletStrategy;

import com.example.user.bulletfalls.Objects.Bullet;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("standard")
public class NoneDoToBulletStrategy implements DoToBulletStrategy {

    @Override
    public void doToBullet(Bullet bullet) {
        bullet.setPower(0);
    }

    @Override
    public void boostAtribute(int boost) {

    }
}
