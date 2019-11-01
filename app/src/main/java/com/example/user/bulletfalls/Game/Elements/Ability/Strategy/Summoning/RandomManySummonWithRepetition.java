package com.example.user.bulletfalls.Game.Elements.Ability.Strategy.Summoning;

import com.example.user.bulletfalls.Game.ActionService.Action;
import com.example.user.bulletfalls.Game.ActionService.ActionType.ActionType;
import com.example.user.bulletfalls.Game.ActionService.Actions.AbilitysActions.SummonAction;
import com.example.user.bulletfalls.Game.Elements.Beast.BeastSpecyfication;
import com.example.user.bulletfalls.Game.Elements.Beast.Beast;
import com.example.user.bulletfalls.Game.Elements.Helper.Summoner;
import com.example.user.bulletfalls.Game.Management.EyeOnGame;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@JsonTypeName("randommannysummonwithrepetition")
public class RandomManySummonWithRepetition extends RandomSummon {
    int number;
    public RandomManySummonWithRepetition(List<BeastSpecyfication> beastList, int number) {
        super(beastList);
        this.number=number;

    }
    private RandomManySummonWithRepetition(){}
    @Override
    public Action prepareAction(EyeOnGame eyeOnGame) {

        List<BeastSpecyfication> beasts= new LinkedList<>();
    for(BeastSpecyfication sb : this.beastSpecyfications)
    {
        beasts.add(sb);
    }
    Random random= new Random();

    return new SummonAction(ActionType.INNER,beasts);
    }


}
