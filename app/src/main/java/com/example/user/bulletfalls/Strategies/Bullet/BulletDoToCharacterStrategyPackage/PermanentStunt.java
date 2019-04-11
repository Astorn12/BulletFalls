package com.example.user.bulletfalls.Strategies.Bullet.BulletDoToCharacterStrategyPackage;

import com.example.user.bulletfalls.Objects.Character;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("permanentstunt")
public class PermanentStunt implements BulletDoToCharacterStrategy {

    int value;
    public PermanentStunt(int value)
    {
        this.value=value;
    }
    public PermanentStunt(){}
    @Override
    public void doToCharacter(Character character) {
        character.slowDown(value);
    }

    @Override
    public BulletDoToCharacterStrategy clone() {
        return new PermanentStunt(this.value);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
