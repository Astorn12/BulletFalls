package com.example.user.bulletfalls.Strategies.Abilities.SummonerPackage.BeastStoragers;

import com.example.user.bulletfalls.Specyfications.Dynamic.Characters.Enemy.BeastSpecyfication;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

@JsonTypeInfo(use = NAME, include = PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value=AsList.class, name = "aslist"),
        @JsonSubTypes.Type(value=Single.class, name = "single"),


})

public interface IBeastStorage {

    @JsonIgnore
    List<BeastSpecyfication> getBeastsStorage();
}
