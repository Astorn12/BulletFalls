package com.example.user.bulletfalls.Strategies.Abilities.Summoning;

import android.content.Context;

import com.example.user.bulletfalls.Objects.Character;
import com.example.user.bulletfalls.Objects.Hero;
import com.example.user.bulletfalls.Objects.Beast;
import com.example.user.bulletfalls.Specyfications.Dynamic.Characters.Enemy.BeastSpecyfication;
import com.example.user.bulletfalls.Strategies.Abilities.DoToCharacterStrategy;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("summon")
    public  class Summon implements DoToCharacterStrategy {
        BeastSpecyfication beastSpecyficationSpecyfication;

    private Summon(){}

    public Summon(BeastSpecyfication beastSpecyfication) {
        this.beastSpecyficationSpecyfication = beastSpecyfication;
    }

    @Override
    public void doToCharacter(Character character) {
        ((Hero) character).summon(getBeast(character.getContext()));

    }

    public Beast getBeast(Context context)
    {
        return new Beast(context,this.beastSpecyficationSpecyfication);
    }

    public BeastSpecyfication getBeastSpecyficationSpecyfication() {
        return beastSpecyficationSpecyfication;
    }

    public void setBeastSpecyficationSpecyfication(BeastSpecyfication beastSpecyficationSpecyfication) {
        this.beastSpecyficationSpecyfication = beastSpecyficationSpecyfication;
    }
}
