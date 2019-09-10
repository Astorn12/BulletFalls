package com.example.user.bulletfalls.Strategies.Abilities.SummonerPackage.BeastChosers;

import com.example.user.bulletfalls.Specyfications.Dynamic.Characters.Enemy.BeastSpecyfication;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

@JsonTypeInfo(use = NAME, include = PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value=RoundRobin.class, name = "roundrobin"),
        @JsonSubTypes.Type(value=AllOfTheme.class, name = "alloftheme"),
        @JsonSubTypes.Type(value=Progress.class, name = "progress")



})
public interface IBeastChoser {

    List<BeastSpecyfication> createBeastsSet(List<BeastSpecyfication> storage, int amount);
}
