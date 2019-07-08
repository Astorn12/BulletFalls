package com.example.user.bulletfalls.Strategies.Abilities.Summoning;

import com.example.user.bulletfalls.Objects.Character;
import com.example.user.bulletfalls.Objects.Beast;
import com.example.user.bulletfalls.Objects.Summoner;
import com.example.user.bulletfalls.Specyfications.Dynamic.Characters.Enemy.BeastSpecyfication;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class RandomManySummon extends RandomSummon {
    int number;
    public RandomManySummon(List<BeastSpecyfication> beastList, int number) {
        super(beastList);
        this.number=number;

    }

    @Override
    public void doToCharacter(Character character) {

        List<BeastSpecyfication> beasts= new LinkedList<>();
    for(BeastSpecyfication sb : this.beastSpecyfications)
    {
        beasts.add(sb);
    }
    Random random= new Random();

        for(int i=0;i<this.number;i++) {
            int x=random.nextInt(beasts.size());
            ((Summoner) character).summon(new Beast(character.getContext(),beasts.get(x)));
            beasts.remove(x);
        }
    }

    @Override
    public BeastSpecyfication getBeast()
    {
        return null;
    }
}
