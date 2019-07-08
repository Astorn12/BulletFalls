package com.example.user.bulletfalls.GameBiznesFunctions.Classes;

import com.example.user.bulletfalls.GameBiznesFunctions.Resistance.Resistance;
import com.example.user.bulletfalls.GameManagement.EyeOnGame;
import com.example.user.bulletfalls.Objects.Hero;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

@JsonTypeInfo(use = NAME, include = PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value=   Mugol.class, name = "mugol"),


})
public interface IClass {
    int getImage();
    String getDescription();
    void action(EyeOnGame eog,Hero hero);
}
