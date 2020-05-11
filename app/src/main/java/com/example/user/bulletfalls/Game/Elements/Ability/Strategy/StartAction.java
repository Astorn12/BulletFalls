package com.example.user.bulletfalls.Game.Elements.Ability.Strategy;

import android.widget.LinearLayout;

import com.example.user.bulletfalls.Game.ActionService.Action;
import com.example.user.bulletfalls.Game.Elements.Ability.Strategy.SummonerPackage.SummonStrategy;
import com.example.user.bulletfalls.Game.Elements.Ability.Strategy.Summoning.Summon;
import com.example.user.bulletfalls.Game.Elements.Ability.Strategy.Summoning.SummonFromList;
import com.example.user.bulletfalls.Game.Elements.Ability.Strategy.TimeCounting.FullCounter;
import com.example.user.bulletfalls.Game.Elements.Ability.Strategy.TimeCounting.TimeJump;
import com.example.user.bulletfalls.Game.Management.EyeOnGame;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

@JsonTypeInfo(use = NAME, include = PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value=   Heal.class, name = "heal"),
        @JsonSubTypes.Type(value=   ChangeBullet.class, name = "changebullet"),
        @JsonSubTypes.Type(value=   SuperShoot.class, name = "supershoot"),
        @JsonSubTypes.Type(value=   Empty.class, name = "empty"),
        @JsonSubTypes.Type(value=   CarpetDiem.class, name = "carpeddiem"),
        @JsonSubTypes.Type(value=   Summon.class, name = "summon"),
        @JsonSubTypes.Type(value=   TimeJump.class, name = "timejump"),
        @JsonSubTypes.Type(value=   FullCounter.class, name = "fullcounter"),
        @JsonSubTypes.Type(value=   SummonFromList.class, name = "summonfromlist"),
        @JsonSubTypes.Type(value=   ShootBooster.class, name = "shootbooster"),
        @JsonSubTypes.Type(value=   SummonStrategy.class, name = "summonstrategy"),
        @JsonSubTypes.Type(value=   ProtectedBall.class, name = "protectedball"),
        @JsonSubTypes.Type(value=   AnimatedStartAction.class, name = "animatedstartaction")

})
public interface StartAction {

    Action prepareAction(EyeOnGame eyeOnGame);
    @JsonIgnore
    String getDescription();
    @JsonIgnore
    void setAdditionalDescription(LinearLayout linearLayout);

}
