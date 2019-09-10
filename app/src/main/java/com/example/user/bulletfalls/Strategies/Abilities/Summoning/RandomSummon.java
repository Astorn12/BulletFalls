package com.example.user.bulletfalls.Strategies.Abilities.Summoning;

import android.widget.LinearLayout;

import com.example.user.bulletfalls.Objects.Character;
import com.example.user.bulletfalls.Objects.Beast;
import com.example.user.bulletfalls.Objects.Description;
import com.example.user.bulletfalls.Objects.Summoner;
import com.example.user.bulletfalls.Specyfications.Dynamic.Characters.Enemy.BeastSpecyfication;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.List;
import java.util.Random;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

@JsonTypeInfo(use = NAME, include = PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value=RandomManySummonWithRepetition.class, name = "randommanysummonwithrepetition")


})
@JsonTypeName("randomsummon")
public class RandomSummon extends SummonFromList {

    @JsonIgnore
    Random random;
    public RandomSummon(List<BeastSpecyfication> beastSpecyfications) {
        super(beastSpecyfications);
        this.random= new Random();
    }

    protected RandomSummon() {
        this.random=new Random();
    }


    @JsonIgnore
    @Override
    public BeastSpecyfication choseBeast() {

        return this.beastSpecyfications.get(random.nextInt(this.beastSpecyfications.size()-1));
    }

    @Override
    public Description description() {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public void setAdditionalDescription(LinearLayout linearLayout) {

    }
}
