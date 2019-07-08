package com.example.user.bulletfalls.Strategies.Character.Character.DoToBulletStrategy.CharacterMoveStrategiesPackage;

import com.example.user.bulletfalls.Objects.Bullet;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

@JsonTypeInfo(use = NAME, include = PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value=NoneDoToBulletStrategy.class, name = "standard"),
        @JsonSubTypes.Type(value=Stot.class, name = "stot"),
        @JsonSubTypes.Type(value=Stot.class, name = "stot"),
})
public interface DoToBulletStrategy {

    public void doToBullet(Bullet bullet);
    public void boostAtribute(int boost);


}
