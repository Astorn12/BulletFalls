package com.example.user.bulletfalls.Strategies.Abilities.SummonerPackage;

import android.widget.LinearLayout;

import com.example.user.bulletfalls.Objects.Beast;
import com.example.user.bulletfalls.Objects.Character;
import com.example.user.bulletfalls.Objects.Hero;
import com.example.user.bulletfalls.Specyfications.Dynamic.Characters.Enemy.BeastSpecyfication;
import com.example.user.bulletfalls.Strategies.Abilities.DoToCharacterStrategy;
import com.example.user.bulletfalls.Strategies.Abilities.SummonerPackage.BeastChosers.IBeastChoser;
import com.example.user.bulletfalls.Strategies.Abilities.SummonerPackage.BeastRaisers.IBeastRaiser;
import com.example.user.bulletfalls.Strategies.Abilities.SummonerPackage.BeastStoragers.IBeastStorage;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.List;


@JsonTypeName("summonstrategy")
public  class SummonStrategy implements ISummonStrategy, DoToCharacterStrategy {
    IBeastStorage storage;
    IBeastRaiser raiser;
    IBeastChoser choser;

    protected SummonStrategy() {
    }

    public SummonStrategy(IBeastStorage storage, IBeastRaiser raiser, IBeastChoser choser) {
        this.storage = storage;
        this.raiser = raiser;
        this.choser = choser;
    }

    @Override
    public void doToCharacter(Character character) {

        for(BeastSpecyfication bs:getBeasts())
        {
            ((Hero) character).summon(new Beast(character.getContext(), bs));
        }

    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public void setAdditionalDescription(LinearLayout linearLayout) {

    }

    @JsonIgnore
    @Override
    public List<BeastSpecyfication> getBeasts() {
        return   this.choser.createBeastsSet(storage.getBeastsStorage(),raiser.beastsGroupSize());
    }

    @Override
    public void describe(LinearLayout linearLayout) {

    }

    public IBeastStorage getStorage() {
        return storage;
    }

    public void setStorage(IBeastStorage storage) {
        this.storage = storage;
    }

    public IBeastRaiser getRaiser() {
        return raiser;
    }

    public void setRaiser(IBeastRaiser raiser) {
        this.raiser = raiser;
    }

    public IBeastChoser getChoser() {
        return choser;
    }

    public void setChoser(IBeastChoser choser) {
        this.choser = choser;
    }
}
