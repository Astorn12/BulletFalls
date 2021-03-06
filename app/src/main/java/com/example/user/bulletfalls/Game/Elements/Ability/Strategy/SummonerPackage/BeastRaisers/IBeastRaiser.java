package com.example.user.bulletfalls.Game.Elements.Ability.Strategy.SummonerPackage.BeastRaisers;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

@JsonTypeInfo(use = NAME, include = PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value=Constant.class, name = "constant"),
        @JsonSubTypes.Type(value=Linear.class, name = "linear"),
        @JsonSubTypes.Type(value=RandomRaiser.class, name = "randomraiser"),
        @JsonSubTypes.Type(value=RandomWithIncrease.class, name = "randomwithincrease"),
        @JsonSubTypes.Type(value=AlwaysOne.class, name = "alwaysone"),


})
public interface IBeastRaiser {
    int beastsGroupSize();
}
