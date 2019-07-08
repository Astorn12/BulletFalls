package com.example.user.bulletfalls.GameBiznesFunctions.Resistance;

import com.example.user.bulletfalls.Strategies.Abilities.CarpetDiem;
import com.example.user.bulletfalls.Strategies.Abilities.ChangeBullet;
import com.example.user.bulletfalls.Strategies.Abilities.Empty;
import com.example.user.bulletfalls.Strategies.Abilities.Heal;
import com.example.user.bulletfalls.Strategies.Abilities.Summoning.Summon;
import com.example.user.bulletfalls.Strategies.Abilities.Summoning.SummonFromList;
import com.example.user.bulletfalls.Strategies.Abilities.SuperShoot;
import com.example.user.bulletfalls.Strategies.Abilities.TimeCounting.FullCounter;
import com.example.user.bulletfalls.Strategies.Abilities.TimeCounting.TimeJump;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

@JsonTypeInfo(use = NAME, include = PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value=   Resistance.class, name = "resistance"),


})
public interface IResistance {
    int reduce(int damage);
    void boost(int x);
    void suppress(int x);
    IResistance clone();
}
