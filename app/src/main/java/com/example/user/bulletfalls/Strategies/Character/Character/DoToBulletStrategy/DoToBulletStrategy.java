package com.example.user.bulletfalls.Strategies.Character.Character.DoToBulletStrategy;

import com.example.user.bulletfalls.ObjectsOfGame.Bullet;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

@JsonTypeInfo(use = NAME, include = PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value=Standard.class, name = "standard"),
        @JsonSubTypes.Type(value=Stot.class, name = "stot")
})
public interface DoToBulletStrategy {

    public void doToBullet(Bullet bullet);
    public void boostAtribute(int boost);


}
