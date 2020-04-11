package com.example.user.bulletfalls.Game.Elements.Helper.Statistics.Active;

import com.example.user.bulletfalls.Game.Elements.BulletManiputatorsPackage.AttackDefenceFilter;
import com.example.user.bulletfalls.Game.Elements.Overal.AppearActionStrategy.AppearAction;
import com.example.user.bulletfalls.Game.GameBiznesFunctions.SuperPowers.MasterAbility;
import com.example.user.bulletfalls.GlobalUsage.Enums.CharacterPositioning;

public class HeroAS extends CharacterAS {
    MasterAbility icalss;

    public HeroAS(CharacterPositioning characterPositioning, AttackDefenceFilter attackDefenceFilter, AppearAction appearAction, MasterAbility icalss) {
        super(characterPositioning, attackDefenceFilter, appearAction);
        this.icalss = icalss;
    }

    public MasterAbility getIcalss() {
        return icalss;
    }

    public void setIcalss(MasterAbility icalss) {
        this.icalss = icalss;
    }
}
