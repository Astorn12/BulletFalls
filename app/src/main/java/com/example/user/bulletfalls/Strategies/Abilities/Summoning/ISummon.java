package com.example.user.bulletfalls.Strategies.Abilities.Summoning;

import com.example.user.bulletfalls.Specyfications.Dynamic.Characters.Enemy.BeastSpecyfication;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

@JsonTypeInfo(use = NAME, include = PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value=Summon.class, name = "summon"),
        @JsonSubTypes.Type(value=SummonFromList.class, name = "summonfromlist"),

})
public interface ISummon {

     BeastSpecyfication getBeast();

}
