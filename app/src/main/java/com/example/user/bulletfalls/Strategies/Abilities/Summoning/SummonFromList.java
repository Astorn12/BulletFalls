package com.example.user.bulletfalls.Strategies.Abilities.Summoning;

import com.example.user.bulletfalls.Objects.Beast;
import com.example.user.bulletfalls.Objects.Character;
import com.example.user.bulletfalls.Objects.Hero;
import com.example.user.bulletfalls.Specyfications.Dynamic.Characters.Enemy.BeastSpecyfication;
import com.example.user.bulletfalls.Strategies.Abilities.DoToCharacterStrategy;
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
public  abstract class SummonFromList implements ISummon,DoToCharacterStrategy {

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
    public  void doToCharacter(Character character){
        ((Hero) character).summon(new Beast(character.getContext(), choseBeast())); }

    @Override
    public abstract BeastSpecyfication choseBeast( );
}
