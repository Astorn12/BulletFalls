package com.example.user.bulletfalls.Strategies.Abilities.SummonerPackage.BeastChosers;

import com.example.user.bulletfalls.Specyfications.Dynamic.Characters.Enemy.BeastSpecyfication;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.LinkedList;
import java.util.List;
@JsonTypeName("roundrobin")
public class RoundRobin implements IBeastChoser{

    int counter;


    @Override
    public List<BeastSpecyfication> createBeastsSet(List<BeastSpecyfication> storage, int amount) {

        List<BeastSpecyfication> chosenBeasts= new LinkedList<>();
        BeastSpecyfication beastSpecyfication = storage.get(counter%storage.size());

        for(int i=0;i<amount;i++)
        {
            chosenBeasts.add(beastSpecyfication);
        }
        counter++;
        return chosenBeasts;
    }
}
