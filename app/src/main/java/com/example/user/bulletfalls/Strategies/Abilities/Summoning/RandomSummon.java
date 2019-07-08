package com.example.user.bulletfalls.Strategies.Abilities.Summoning;

import com.example.user.bulletfalls.Objects.Character;
import com.example.user.bulletfalls.Objects.Beast;
import com.example.user.bulletfalls.Objects.Summoner;
import com.example.user.bulletfalls.Specyfications.Dynamic.Characters.Enemy.BeastSpecyfication;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.List;
import java.util.Random;
@JsonTypeName("randomsummon")
public class RandomSummon extends SummonFromList {

    @JsonIgnore
    Random random;
    public RandomSummon(List<BeastSpecyfication> beastSpecyfications) {
        super(beastSpecyfications);
        this.random= new Random();
    }

    protected RandomSummon() {
        this.random=new Random();
    }

    @Override
    public void doToCharacter(Character character) {
        ((Summoner) character).summon(new Beast(character.getContext(),getBeast()));
    }

    @JsonIgnore
    @Override
    public BeastSpecyfication getBeast() {

        return this.beastSpecyfications.get(random.nextInt(this.beastSpecyfications.size()-1));
    }
}
