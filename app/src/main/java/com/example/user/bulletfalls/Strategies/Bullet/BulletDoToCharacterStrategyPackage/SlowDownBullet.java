package com.example.user.bulletfalls.Strategies.Bullet.BulletDoToCharacterStrategyPackage;

import com.example.user.bulletfalls.Objects.Character;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("slowdownbullet")
public class SlowDownBullet implements BulletDoToCharacterStrategy {
    int slowDownRate;

    public SlowDownBullet(int slowDownRate)
    {
        this.slowDownRate=slowDownRate;
    }
    public SlowDownBullet()
    {

    }

    @Override
    public void doToCharacter(Character character) {
        character.getBullet().slowDown(slowDownRate);
    }

    @Override
    public BulletDoToCharacterStrategy clone() {
        return new SlowDownBullet(this.slowDownRate);
    }
}
