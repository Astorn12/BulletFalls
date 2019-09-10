package com.example.user.bulletfalls.Strategies.Abilities.SummonerPackage.BeastRaisers;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("constant")
public class Constant implements  IBeastRaiser{
    int amount;

    private Constant() {

    }

    public Constant(int amount) {
        this.amount = amount;
    }

    @Override
    public int beastsGroupSize() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
