package com.example.user.bulletfalls.Profile.Collection.HeroCollection.FiltersAndSorters;

import com.example.user.bulletfalls.Game.Elements.Hero.HeroSpecyfication;
import com.example.user.bulletfalls.Game.GameBiznesFunctions.Classes.MasterAbility;

public class MasterAbilityFilter extends SmartFilter<HeroSpecyfication> {

    MasterAbility masterAbility;

    public MasterAbilityFilter(MasterAbility masterAbility) {
        this.masterAbility = masterAbility;
    }

    public MasterAbility getMasterAbility() {
        return masterAbility;
    }

    public void setMasterAbility(MasterAbility masterAbility) {
        this.masterAbility = masterAbility;
    }

    @Override
    protected boolean ifMatchFilter(HeroSpecyfication heroSpecyfication) {
        boolean f= heroSpecyfication.getMasterAbility().getClass().equals(this.masterAbility.getClass());
        return f;
    }
}
