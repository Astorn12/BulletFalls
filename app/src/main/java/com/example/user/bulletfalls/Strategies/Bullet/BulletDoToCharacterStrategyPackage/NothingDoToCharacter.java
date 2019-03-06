package com.example.user.bulletfalls.Strategies.Bullet.BulletDoToCharacterStrategyPackage;

import com.example.user.bulletfalls.Character;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("nothingtodocharacter")
public class NothingDoToCharacter implements BulletDoToCharacterStrategy {
    public NothingDoToCharacter(){}
    @Override
    public void doToCharacter(Character character) {

    }

    @Override
    public BulletDoToCharacterStrategy clone() {
        return new NothingDoToCharacter();
    }
}
