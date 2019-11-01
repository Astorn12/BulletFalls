package com.example.user.bulletfalls.Game.Elements.Helper.Statistics.Collection;

import com.example.user.bulletfalls.Game.Elements.Helper.Statistics.Active.CharacterAS;
import com.example.user.bulletfalls.GlobalUsage.Enums.FamilyName;
import com.example.user.bulletfalls.GlobalUsage.Enums.Kind;
import com.example.user.bulletfalls.Shop.PossesStrategyPackage.PossesStrategy;

import java.util.List;

public class HeroCS extends CharacterCS {
    PossesStrategy possesStrategy;
    int tier;

    public HeroCS(String indyvidualHeroMarker, List<FamilyName> familyNames, List<Kind> kind, PossesStrategy possesStrategy, int tier) {
        super(indyvidualHeroMarker, familyNames, kind);
        this.possesStrategy = possesStrategy;
        this.tier = tier;
    }

    public PossesStrategy getPossesStrategy() {
        return possesStrategy;
    }

    public void setPossesStrategy(PossesStrategy possesStrategy) {
        this.possesStrategy = possesStrategy;
    }

    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }
}
