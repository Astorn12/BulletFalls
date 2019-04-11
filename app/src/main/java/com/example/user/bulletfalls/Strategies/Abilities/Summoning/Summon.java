package com.example.user.bulletfalls.Strategies.Abilities.Summoning;

import android.content.Context;

import com.example.user.bulletfalls.Objects.Character;
import com.example.user.bulletfalls.Objects.Hero;
import com.example.user.bulletfalls.Objects.SummonedBeast;
import com.example.user.bulletfalls.Specyfications.Characters.SummonedBeastSpecyfication;
import com.example.user.bulletfalls.Strategies.Abilities.DoToCharacterStrategy;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("summon")
    public  class Summon implements DoToCharacterStrategy {
        SummonedBeastSpecyfication summonedBeastSpecyfication;

    private Summon(){}

    public Summon(SummonedBeastSpecyfication summonedBeast) {
        this.summonedBeastSpecyfication = summonedBeast;
    }

    @Override
    public void doToCharacter(Character character) {
        ((Hero)character).summon(getBeast(character.getContext()));

    }

    public  SummonedBeast getBeast(Context context)
    {
        return new SummonedBeast(context,this.summonedBeastSpecyfication);
    }

    public SummonedBeastSpecyfication getSummonedBeastSpecyfication() {
        return summonedBeastSpecyfication;
    }

    public void setSummonedBeastSpecyfication(SummonedBeastSpecyfication summonedBeastSpecyfication) {
        this.summonedBeastSpecyfication = summonedBeastSpecyfication;
    }
}
