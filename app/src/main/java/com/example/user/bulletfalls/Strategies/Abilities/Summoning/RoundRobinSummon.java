package com.example.user.bulletfalls.Strategies.Abilities.Summoning;

import android.widget.LinearLayout;

import com.example.user.bulletfalls.Objects.Character;
import com.example.user.bulletfalls.Objects.Description;
import com.example.user.bulletfalls.Specyfications.Dynamic.Characters.Enemy.BeastSpecyfication;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;


@JsonTypeName("roundrobinsummon")
public class RoundRobinSummon extends SummonFromList {
    int counter;

    public RoundRobinSummon(List<BeastSpecyfication> beastSpecyfications) {
        super(beastSpecyfications);
        counter=0;
    }

    private RoundRobinSummon(){}



    @Override
    public BeastSpecyfication choseBeast() {
    BeastSpecyfication ret=this.beastSpecyfications.get(counter);
    counter++;
    if(counter>=this.beastSpecyfications.size())counter=0;
    return ret;
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
