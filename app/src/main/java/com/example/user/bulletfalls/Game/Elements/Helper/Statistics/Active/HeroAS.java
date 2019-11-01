package com.example.user.bulletfalls.Game.Elements.Helper.Statistics.Active;

import com.example.user.bulletfalls.Game.Elements.Ability.AbilitiesBar;
import com.example.user.bulletfalls.Game.Elements.BulletManiputatorsPackage.AttackDefenceFilter;
import com.example.user.bulletfalls.Game.Elements.Overal.AppearActionStrategy.AppearAction;
import com.example.user.bulletfalls.Game.GameBiznesFunctions.Classes.IClass;
import com.example.user.bulletfalls.GlobalUsage.Enums.CharacterPositioning;

public class HeroAS extends CharacterAS {
    IClass icalss;

    public HeroAS(CharacterPositioning characterPositioning, AttackDefenceFilter attackDefenceFilter, AppearAction appearAction, IClass icalss) {
        super(characterPositioning, attackDefenceFilter, appearAction);
        this.icalss = icalss;
    }

    public IClass getIcalss() {
        return icalss;
    }

    public void setIcalss(IClass icalss) {
        this.icalss = icalss;
    }
}
