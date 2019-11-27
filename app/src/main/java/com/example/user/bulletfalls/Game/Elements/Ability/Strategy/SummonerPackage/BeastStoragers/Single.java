package com.example.user.bulletfalls.Game.Elements.Ability.Strategy.SummonerPackage.BeastStoragers;

import com.example.user.bulletfalls.Game.Elements.Beast.BeastSpecyfication;
import com.example.user.bulletfalls.GlobalUsage.Exceptions.IncorrectBeastNameException;
import com.example.user.bulletfalls.Storage.Sets.BeastsSet;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.LinkedList;
import java.util.List;

@JsonTypeName("single")
public class Single implements IBeastStorage{
    BeastSpecyfication bs;
    public Single(BeastSpecyfication beastSpecyfication)
    {
        this.bs=beastSpecyfication;
    }
    public Single()
    {

    }

    public Single(String name)
    {
        try {
            this.bs=BeastsSet.getInstance().getByName(name);
        } catch (IncorrectBeastNameException e) {
            e.printStackTrace();
        }
    }
    public BeastSpecyfication getBs() {
        return bs;
    }

    public void setBs(BeastSpecyfication bs) {
        this.bs = bs;
    }

    @Override
    public List<BeastSpecyfication> getBeastsStorage() {

        List<BeastSpecyfication> beastSpecyfications= new LinkedList<>();
        beastSpecyfications.add(this.bs);
        return beastSpecyfications;

    }
}
