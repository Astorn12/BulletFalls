package com.example.user.bulletfalls.Profile.Collection.HeroCollection.FiltersAndSorters;

import com.example.user.bulletfalls.Game.Elements.Hero.FamilyPackage.Family;
import com.example.user.bulletfalls.Game.Elements.Hero.HeroSpecyfication;
import com.example.user.bulletfalls.GlobalUsage.Enums.FamilyName;

public class FamilyFilter extends SmartFilter<HeroSpecyfication> {

    FamilyName familyName;

    public FamilyFilter(FamilyName familyName) {
        this.familyName = familyName;
    }

    @Override
    protected boolean ifMatchFilter(HeroSpecyfication heroSpecyfication) {
        return heroSpecyfication.isFromFamiy(this.familyName);
    }
}
