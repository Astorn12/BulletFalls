package com.example.user.bulletfalls.Game.Elements.Ability.Strategy.SummonerPackage.BeastRaisers;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("alwaysone")
public class AlwaysOne implements IBeastRaiser {
    @Override
    public int beastsGroupSize() {
        return 1;
    }
}
