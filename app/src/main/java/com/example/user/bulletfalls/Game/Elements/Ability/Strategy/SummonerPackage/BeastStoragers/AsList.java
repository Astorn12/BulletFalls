package com.example.user.bulletfalls.Game.Elements.Ability.Strategy.SummonerPackage.BeastStoragers;

import com.example.user.bulletfalls.Game.Elements.Beast.BeastSpecyfication;
import com.example.user.bulletfalls.Storage.Sets.BeastsSet;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.List;
@JsonTypeName("aslist")
public class AsList implements IBeastStorage {

    List<BeastSpecyfication> beastStorage;


    public AsList(List<BeastSpecyfication> beastStoragel) {
        this.beastStorage = beastStoragel;
    }

    public AsList(String... parameters) {
        this.beastStorage= BeastsSet.getInstance().getChosen(parameters);
    }


    private AsList() {
    }


    @Override
    public List<BeastSpecyfication> getBeastsStorage() {
        return this.beastStorage;
    }

    public List<BeastSpecyfication> getBeastStorage() {
        return beastStorage;
    }

    public void setBeastStorage(List<BeastSpecyfication> beastStorage) {
        this.beastStorage = beastStorage;
    }
}
