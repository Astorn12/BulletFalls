package com.example.user.bulletfalls.Strategies.Bullet.BulletDoToCharacterStrategyPackage;

import com.example.user.bulletfalls.Objects.Character;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("nothingtodocharacter")
public class NoneBulletDoToCharacterStrategy implements BulletDoToCharacterStrategy {
    public NoneBulletDoToCharacterStrategy(){}
    @Override
    public void doToCharacter(Character character) {

    }

    @Override
    public BulletDoToCharacterStrategy clone() {
        return new NoneBulletDoToCharacterStrategy();
    }
}
