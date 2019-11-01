package com.example.user.bulletfalls.Game.Elements.Ability.Strategy.SummonerPackage.BeastChosers;

import com.example.user.bulletfalls.Game.Elements.Beast.BeastSpecyfication;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.LinkedList;
import java.util.List;
@JsonTypeName("alloftheme")
public class AllOfTheme implements IBeastChoser {
    @Override
    public List<BeastSpecyfication> createBeastsSet(List<BeastSpecyfication> storage, int amount) {

        List<BeastSpecyfication> list= new LinkedList<>();

        for(BeastSpecyfication b: storage)
        {
            for(int i=0;i<amount;i++)
            {
                list.add(b);
            }
        }

        return list;
    }
}
