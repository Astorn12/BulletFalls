package com.example.user.bulletfalls.Strategies.Bullet.BulletDoToCharacterStrategyPackage;

import com.example.user.bulletfalls.Objects.Character;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("bulletweaken")
public class BulletWeaken implements BulletDoToCharacterStrategy {
    int fall;

    public BulletWeaken(){}
    public BulletWeaken(int fall){
        this.fall=fall;
    }

    @Override
    public void doToCharacter(Character character) {
        character.getBullet().weaken(fall);
    }

    @Override
    public BulletDoToCharacterStrategy clone() {
        return new BulletWeaken(this.fall);
    }
}
