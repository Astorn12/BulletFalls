package com.example.user.bulletfalls.Game.ActionService.Actions.ActionsAnimations;

import android.graphics.drawable.AnimationDrawable;

import com.example.user.bulletfalls.Game.ActionService.Actions.ActionsAnimations.AnimationListeners.AnimationController;
import com.example.user.bulletfalls.Game.Activities.Game;
import com.example.user.bulletfalls.Game.Elements.Ability.Strategy.CarpetDiem;
import com.example.user.bulletfalls.Game.Elements.Ability.Strategy.ChangeBullet;
import com.example.user.bulletfalls.Game.Elements.Ability.Strategy.Empty;
import com.example.user.bulletfalls.Game.Elements.Ability.Strategy.Heal;
import com.example.user.bulletfalls.Game.Elements.Ability.Strategy.ProtectedBall;
import com.example.user.bulletfalls.Game.Elements.Ability.Strategy.ShootBooster;
import com.example.user.bulletfalls.Game.Elements.Ability.Strategy.SummonerPackage.SummonStrategy;
import com.example.user.bulletfalls.Game.Elements.Ability.Strategy.Summoning.Summon;
import com.example.user.bulletfalls.Game.Elements.Ability.Strategy.Summoning.SummonFromList;
import com.example.user.bulletfalls.Game.Elements.Ability.Strategy.SuperShoot;
import com.example.user.bulletfalls.Game.Elements.Ability.Strategy.TimeCounting.FullCounter;
import com.example.user.bulletfalls.Game.Elements.Ability.Strategy.TimeCounting.TimeJump;
import com.example.user.bulletfalls.Game.Management.EyeOnGame;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

@JsonTypeInfo(use = NAME, include = PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value=   HeroAnimation.class, name = "heroanimation"),


})
public  interface GameAnimation {

    AnimationController animate(EyeOnGame eyeOnGame);
    boolean willBeAnimated(EyeOnGame eyeOnGame);

}
