package com.example.user.bulletfalls.Game.Elements.Helper.Statistics.Collection;

import com.example.user.bulletfalls.GlobalUsage.Enums.Rarity;
import com.example.user.bulletfalls.Shop.PossesStrategyPackage.PossesStrategy;

public class BulletCS {
    Rarity rarity;
    PossesStrategy possesStrategy;

    public BulletCS(Rarity rarity, PossesStrategy possesStrategy) {
        this.rarity = rarity;
        this.possesStrategy = possesStrategy;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
    }

    public PossesStrategy getPossesStrategy() {
        return possesStrategy;
    }

    public void setPossesStrategy(PossesStrategy possesStrategy) {
        this.possesStrategy = possesStrategy;
    }
}
