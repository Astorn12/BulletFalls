package com.example.user.bulletfalls.Game.Elements.Ability.Strategy.Summoning;

import android.widget.LinearLayout;

import com.example.user.bulletfalls.Game.Elements.Beast.BeastSpecyfication;
import com.example.user.bulletfalls.Game.Elements.Helper.Description;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.List;


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
