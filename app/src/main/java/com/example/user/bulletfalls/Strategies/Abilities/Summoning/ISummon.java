package com.example.user.bulletfalls.Strategies.Abilities.Summoning;

import com.example.user.bulletfalls.Objects.SummonedBeast;
import com.example.user.bulletfalls.Specyfications.Characters.SummonedBeastSpecyfication;
import com.example.user.bulletfalls.Strategies.Character.Character.DoToBulletStrategy.NoneDoToBulletStrategy;
import com.example.user.bulletfalls.Strategies.Character.Character.DoToBulletStrategy.Stot;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

@JsonTypeInfo(use = NAME, include = PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value=Summon.class, name = "summon"),

})
public interface ISummon {

     SummonedBeast getBeast();

}
