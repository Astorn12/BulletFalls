package com.example.user.bulletfalls.Game.Elements.Ability.Strategy.SummonerPackage;

import android.widget.LinearLayout;

import com.example.user.bulletfalls.Game.ActionService.Action;
import com.example.user.bulletfalls.Game.ActionService.ActionType.ActionType;
import com.example.user.bulletfalls.Game.ActionService.Actions.AbilitysActions.SummonAction;
import com.example.user.bulletfalls.Game.Elements.Beast.Beast;
import com.example.user.bulletfalls.Game.Elements.Beast.BeastSpecyfication;
import com.example.user.bulletfalls.Game.Elements.Hero.Hero;
import com.example.user.bulletfalls.Game.Elements.Ability.Strategy.StartAction;
import com.example.user.bulletfalls.Game.Elements.Ability.Strategy.SummonerPackage.BeastChosers.IBeastChoser;
import com.example.user.bulletfalls.Game.Elements.Ability.Strategy.SummonerPackage.BeastRaisers.IBeastRaiser;
import com.example.user.bulletfalls.Game.Elements.Ability.Strategy.SummonerPackage.BeastStoragers.IBeastStorage;
import com.example.user.bulletfalls.Game.Management.EyeOnGame;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.List;


@JsonTypeName("summonstrategy")
public  class SummonStrategy implements ISummonStrategy, StartAction {
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
    public Action prepareAction(EyeOnGame eyeOnGame) {

       /* for(BeastSpecyfication bs:getBeasts())
        {
            ((Hero) eyeOnGame).summon(new Beast(eyeOnGame.getContext(), bs));
        }*/

        return new SummonAction(ActionType.INNER,getBeasts());
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
