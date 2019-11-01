package com.example.user.bulletfalls.Game.Elements.Ability.Strategy.Summoning;

import com.example.user.bulletfalls.Game.ActionService.Action;
import com.example.user.bulletfalls.Game.ActionService.ActionType.ActionType;
import com.example.user.bulletfalls.Game.ActionService.Actions.AbilitysActions.SummonAction;
import com.example.user.bulletfalls.Game.Elements.Beast.Beast;
import com.example.user.bulletfalls.Game.Elements.Beast.BeastSpecyfication;
import com.example.user.bulletfalls.Game.Elements.Hero.Hero;
import com.example.user.bulletfalls.Game.Elements.Ability.Strategy.StartAction;
import com.example.user.bulletfalls.Game.Management.EyeOnGame;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

@JsonTypeInfo(use = NAME, include = PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value=Summon.class, name = "summon"),
        @JsonSubTypes.Type(value=RandomSummon.class, name = "randomsummon"),
        @JsonSubTypes.Type(value=RoundRobinSummon.class, name = "roundrobinsummon"),
        @JsonSubTypes.Type(value=RandomSummon.class, name = "randomsummon"),
        @JsonSubTypes.Type(value=ProgressSummoner.class, name = "progresssummon"),

})
@JsonTypeName("summonfromlist")
public  abstract class SummonFromList implements ISummon,StartAction {

    List<BeastSpecyfication> beastSpecyfications;

    public  SummonFromList(List<BeastSpecyfication> beastSpecyfications) {
        this.beastSpecyfications = beastSpecyfications;
    }

    protected SummonFromList() {
    }

    public void setBeastSpecyfications(List<BeastSpecyfication> beastSpecyfications) {
        this.beastSpecyfications = beastSpecyfications;
    }

    public List<BeastSpecyfication> getBeastSpecyfications() {
        return beastSpecyfications;
    }

    @Override
    public Action prepareAction(EyeOnGame eyeOnGame){

        return new SummonAction(ActionType.INNER,choseBeast());
    }


    @Override
    public abstract BeastSpecyfication choseBeast( );
}
