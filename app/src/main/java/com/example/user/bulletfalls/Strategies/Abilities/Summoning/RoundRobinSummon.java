package com.example.user.bulletfalls.Strategies.Abilities.Summoning;

import com.example.user.bulletfalls.Objects.Character;
import com.example.user.bulletfalls.Objects.SummonedBeast;
import com.example.user.bulletfalls.Strategies.Abilities.DoToCharacterStrategy;

import java.util.List;

public class RoundRobinSummon extends SummonFromList {


    public RoundRobinSummon(List<SummonedBeast> summonedBeasts) {
        super(summonedBeasts);
    }

    @Override
    public void doToCharacter(Character character) {

    }

    @Override
    public SummonedBeast getBeast() {
        return null;
    }
}
