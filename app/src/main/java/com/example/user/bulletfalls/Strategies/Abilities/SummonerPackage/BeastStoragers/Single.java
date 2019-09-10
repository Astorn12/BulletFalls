package com.example.user.bulletfalls.Strategies.Abilities.SummonerPackage.BeastStoragers;

import com.example.user.bulletfalls.Sets.BeastsSet;
import com.example.user.bulletfalls.Specyfications.Dynamic.Characters.Enemy.BeastSpecyfication;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.type.CollectionLikeType;

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
        this.bs=BeastsSet.getInstance().getByName(name);
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
