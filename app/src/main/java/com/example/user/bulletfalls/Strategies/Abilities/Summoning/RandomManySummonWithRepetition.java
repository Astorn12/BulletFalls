package com.example.user.bulletfalls.Strategies.Abilities.Summoning;

import com.example.user.bulletfalls.Objects.Character;
import com.example.user.bulletfalls.Objects.Beast;
import com.example.user.bulletfalls.Objects.Summoner;
import com.example.user.bulletfalls.Specyfications.Dynamic.Characters.Enemy.BeastSpecyfication;
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
    public void doToCharacter(Character character) {

        List<BeastSpecyfication> beasts= new LinkedList<>();
    for(BeastSpecyfication sb : this.beastSpecyfications)
    {
        beasts.add(sb);
    }
    Random random= new Random();

        for(int i=0;i<this.number;i++) {
            ((Summoner) character).summon(new Beast(character.getContext(), choseBeast()));
        }
    }


}
