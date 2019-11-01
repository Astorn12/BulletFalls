package com.example.user.bulletfalls.Game.Elements.Ability.Strategy.Summoning;

import android.widget.LinearLayout;

import com.example.user.bulletfalls.Game.ActionService.Action;
import com.example.user.bulletfalls.Game.ActionService.ActionType.ActionType;
import com.example.user.bulletfalls.Game.ActionService.Actions.AbilitysActions.SummonAction;
import com.example.user.bulletfalls.Game.Elements.Beast.BeastSpecyfication;
import com.example.user.bulletfalls.Game.Elements.Helper.Description;
import com.example.user.bulletfalls.Game.Elements.Hero.Hero;
import com.example.user.bulletfalls.Game.Elements.Beast.Beast;
import com.example.user.bulletfalls.Game.Elements.Ability.Strategy.StartAction;
import com.example.user.bulletfalls.Game.Management.EyeOnGame;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

@JsonTypeInfo(use = NAME, include = PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value=ProgressAmountMassSummoner.class, name = "progressamountmasssummoner")


})
@JsonTypeName("summon")
    public  class Summon implements StartAction,ISummon {
        BeastSpecyfication beastSpecyficationSpecyfication;

    protected Summon(){}

    public Summon(BeastSpecyfication beastSpecyfication) {
        this.beastSpecyficationSpecyfication = beastSpecyfication;
    }

    @Override
    public Action prepareAction(EyeOnGame eyeOnGame) {
        //((Hero) eyeOnGame).summon(new Beast(eyeOnGame.getContext(), choseBeast()));
        return new SummonAction(ActionType.INNER,this.beastSpecyficationSpecyfication);

    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public void setAdditionalDescription(LinearLayout linearLayout) {

    }

    public BeastSpecyfication getBeastSpecyficationSpecyfication() {
        return beastSpecyficationSpecyfication;
    }

    public void setBeastSpecyficationSpecyfication(BeastSpecyfication beastSpecyficationSpecyfication) {
        this.beastSpecyficationSpecyfication = beastSpecyficationSpecyfication;
    }


    @Override
    public BeastSpecyfication choseBeast() {
        return this.beastSpecyficationSpecyfication;
    }

    @Override
     public Description description() {
        return null;
    }
}
