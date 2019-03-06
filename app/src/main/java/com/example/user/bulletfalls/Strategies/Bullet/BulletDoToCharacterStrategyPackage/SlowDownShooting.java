package com.example.user.bulletfalls.Strategies.Bullet.BulletDoToCharacterStrategyPackage;

import com.example.user.bulletfalls.Character;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("slowdownshooting")
public class SlowDownShooting implements BulletDoToCharacterStrategy {
    int shootingDecrease;
    public SlowDownShooting(int shootingDecrease)
    {
        this.shootingDecrease=shootingDecrease;
    }
    public SlowDownShooting()
    {

    }

    @Override
    public void doToCharacter(Character character) {
        character.slowDownShooting(shootingDecrease);
    }

    @Override
    public BulletDoToCharacterStrategy clone() {
        return new SlowDownShooting(this.shootingDecrease);
    }
}
