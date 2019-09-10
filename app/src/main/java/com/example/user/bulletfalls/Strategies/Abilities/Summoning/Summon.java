package com.example.user.bulletfalls.Strategies.Abilities.Summoning;

import android.widget.LinearLayout;

import com.example.user.bulletfalls.Objects.Character;
import com.example.user.bulletfalls.Objects.Description;
import com.example.user.bulletfalls.Objects.Hero;
import com.example.user.bulletfalls.Objects.Beast;
import com.example.user.bulletfalls.Specyfications.Dynamic.Characters.Enemy.BeastSpecyfication;
import com.example.user.bulletfalls.Strategies.Abilities.DoToCharacterStrategy;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    public  class Summon implements DoToCharacterStrategy,ISummon {
        BeastSpecyfication beastSpecyficationSpecyfication;

    protected Summon(){}

    public Summon(BeastSpecyfication beastSpecyfication) {
        this.beastSpecyficationSpecyfication = beastSpecyfication;
    }

    @Override
    public void doToCharacter(Character character) {
        ((Hero) character).summon(new Beast(character.getContext(), choseBeast()));
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
