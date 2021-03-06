package com.example.user.bulletfalls.Game.Elements.Ability.Strategy.Summoning;

import com.example.user.bulletfalls.Game.Elements.Beast.BeastSpecyfication;
import com.example.user.bulletfalls.Game.Elements.Helper.Description;
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

     BeastSpecyfication choseBeast();
     Description description();

}
