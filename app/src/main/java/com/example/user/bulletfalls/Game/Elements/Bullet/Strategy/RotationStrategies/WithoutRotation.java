package com.example.user.bulletfalls.Game.Elements.Bullet.Strategy.RotationStrategies;

import com.example.user.bulletfalls.Game.Elements.Bullet.Bullet;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("withoutrotation")
public class WithoutRotation implements IRotationStrategy{
    @Override
    public void rotate(Bullet bullet) {

    }
}
