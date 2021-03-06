package com.example.user.bulletfalls.Game.Elements.Ability.Strategy.Summoning;

import android.widget.LinearLayout;

import com.example.user.bulletfalls.Game.Elements.Beast.BeastSpecyfication;
import com.example.user.bulletfalls.Game.Elements.Helper.Description;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.List;

@JsonTypeName("progresssummon")
public class ProgressSummoner extends SummonFromList {
    int counter;
    private ProgressSummoner()
    {

    }
    public  ProgressSummoner(List<BeastSpecyfication> beastSpecyfications) {
        super(beastSpecyfications);
        this.counter=0;
    }

    @JsonIgnore
    @Override
    public BeastSpecyfication choseBeast() {
        BeastSpecyfication ret=this.beastSpecyfications.get(counter);
        counter++;
        if(counter>=this.beastSpecyfications.size()) counter=this.beastSpecyfications.size()-1;
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
