package com.example.user.bulletfalls.Game.GameBiznesFunctions.Classes;

import android.content.Context;

import com.example.user.bulletfalls.Game.ActionService.Action;
import com.example.user.bulletfalls.Game.ActionService.Actions.ClassActions.ClassAction;
import com.example.user.bulletfalls.Game.Management.EyeOnGame;
import com.example.user.bulletfalls.Game.Elements.Hero.Hero;

import com.example.user.bulletfalls.Profile.UserProfile;
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
public abstract class MasterAbility {
    LevelTable levelTable;
    protected  int timeQuant=200;

    @JsonIgnore
    public abstract int getImage();
    @JsonIgnore
    public abstract String getDescription();
    public abstract ClassAction action(EyeOnGame eog);

    protected int getValueAccordingToLevel(Context context){
        UserProfile userProfile=new UserProfile(context);

        int currentLevel=userProfile.getLevel().getNumber();
        return levelTable.getBoostAccordingToLevel(currentLevel);
    }
}
