package com.example.user.bulletfalls.Game.Elements.Overal.MoveStrategyPackage;

import android.graphics.Point;

import com.example.user.bulletfalls.Game.Management.EyeOnGame;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

@JsonTypeInfo(use = NAME, include = PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value=Follower.class, name = "follower"),
        @JsonSubTypes.Type(value=UpDownMove.class, name = "updownmove")
})
public interface CharacterMoveStrategy {
/** zwraca tylko inta bo wszyscy bohaterowie poruszają się tylko w poziomie*/
    int getQuantum(int speed, EyeOnGame gameInterface, Point actualPoint);


}
