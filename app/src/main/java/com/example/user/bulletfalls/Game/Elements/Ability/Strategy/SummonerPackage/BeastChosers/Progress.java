package com.example.user.bulletfalls.Game.Elements.Ability.Strategy.SummonerPackage.BeastChosers;

import com.example.user.bulletfalls.Game.Elements.Beast.BeastSpecyfication;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.LinkedList;
import java.util.List;
@JsonTypeName("progress")
public class Progress implements IBeastChoser {

    int ticker;
    int counter;
    int repetition;

    public Progress(int repetition) {
        this.repetition = repetition;
        ticker=0;
        counter=0;
    }

    protected Progress() {
        ticker=0;
        counter=0;
    }

    @Override
    public List<BeastSpecyfication> createBeastsSet(List<BeastSpecyfication> storage, int amount) {

        BeastSpecyfication b= storage.get(counter);
        if(ticker%repetition==0 & counter<storage.size()-1) counter++;
        ticker++;
        List<BeastSpecyfication> bs= new LinkedList<>();
        for(int i=0;i<amount;i++)
        {
            bs.add(b);
        }
        return  bs;
    }

    public int getRepetition() {
        return repetition;
    }

    public void setRepetition(int repetition) {
        this.repetition = repetition;
    }
}
