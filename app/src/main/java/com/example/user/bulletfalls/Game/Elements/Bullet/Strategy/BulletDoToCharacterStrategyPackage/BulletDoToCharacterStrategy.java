package com.example.user.bulletfalls.Game.Elements.Bullet.Strategy.BulletDoToCharacterStrategyPackage;


import android.widget.LinearLayout;

import com.example.user.bulletfalls.Game.Elements.Helper.Character;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;


@JsonTypeInfo(use = NAME, include = PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value=Disarm.class, name = "disarm"),
        @JsonSubTypes.Type(value=Dizzy.class, name = "dizzy"),
        @JsonSubTypes.Type(value=PermanentStunt.class, name = "permanentstunt"),
        @JsonSubTypes.Type(value=Stunt.class, name = "stunt"),
        @JsonSubTypes.Type(value=Poison.class, name = "poison"),
        @JsonSubTypes.Type(value=Mix.class, name = "mix"),
        @JsonSubTypes.Type(value=NoneBulletDoToCharacterStrategy.class, name = "nothingtodocharacter"),
        @JsonSubTypes.Type(value=SlowDownShooting.class, name = "slowdownshooting"),
        @JsonSubTypes.Type(value=TimeSlowDownShooting.class, name = "timeslowdownshooting"),
        @JsonSubTypes.Type(value=BulletWeaken.class, name = "bulletweaken"),
        @JsonSubTypes.Type(value=SlowDownBullet.class, name = "slowdownbullet"),
        @JsonSubTypes.Type(value=RotationCrit.class, name = "rotationcrit")

})
public interface BulletDoToCharacterStrategy {


    public void doToCharacter(Character character);

    public BulletDoToCharacterStrategy clone();

    public void showOwnDescription(LinearLayout linearLayout);
}
