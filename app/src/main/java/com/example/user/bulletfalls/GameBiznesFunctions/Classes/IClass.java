package com.example.user.bulletfalls.GameBiznesFunctions.Classes;

import com.example.user.bulletfalls.GameBiznesFunctions.Resistance.Resistance;
import com.example.user.bulletfalls.GameManagement.EyeOnGame;
import com.example.user.bulletfalls.Objects.Hero;

import com.example.user.bulletfalls.Strategies.Abilities.SuperShoot;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

@JsonTypeInfo(use = NAME, include = PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value=   Mugol.class, name = "mugol"),
        @JsonSubTypes.Type(value=   AngelProtector.class, name = "angelprotector"),
        @JsonSubTypes.Type(value=   HealerC.class, name = "healer2"),
        @JsonSubTypes.Type(value=   MassDestructor.class, name = "massdestructor"),
        @JsonSubTypes.Type(value=   SuperShooter.class, name = "supershooter"),
        @JsonSubTypes.Type(value=   Breeder.class, name = "breeder")
})
public interface IClass {
    @JsonIgnore
    int getImage();
    @JsonIgnore
    String getDescription();
    void action(EyeOnGame eog,Hero hero);
}
