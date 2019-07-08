package com.example.user.bulletfalls.Strategies.Abilities.Summoning;

import com.example.user.bulletfalls.Objects.Character;
import com.example.user.bulletfalls.Specyfications.Dynamic.Characters.Enemy.BeastSpecyfication;

import java.util.List;

public class RoundRobinSummon extends SummonFromList {


    public RoundRobinSummon(List<BeastSpecyfication> beastSpecyfications) {
        super(beastSpecyfications);
    }

    @Override
    public void doToCharacter(Character character) {

    }

    @Override
    public BeastSpecyfication getBeast() {
        return null;
    }
}
