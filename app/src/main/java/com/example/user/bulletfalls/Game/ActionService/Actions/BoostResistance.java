package com.example.user.bulletfalls.Game.ActionService.Actions;

import com.example.user.bulletfalls.Game.ActionService.Action;
import com.example.user.bulletfalls.Game.ActionService.ActionType.ActionType;
import com.example.user.bulletfalls.Game.Elements.BulletManiputatorsPackage.DefenceFilter;
import com.example.user.bulletfalls.Game.Elements.BulletManiputatorsPackage.DeffenceFilters.Resistance;
import com.example.user.bulletfalls.Game.Management.EyeOnGame;

import java.util.List;

public class BoostResistance extends BoostAction {

    private int boost;
    private int bottom;
    public BoostResistance(ActionType type, int boost,int bottom) {
        super(type, boost);
        this.boost=boost;
        this.bottom=bottom;
    }

    @Override
    public void doMagic(EyeOnGame eyeOnGame) {

        eyeOnGame.getHero().getAttackDefenceFilter().boostDefence(new Resistance(this.boost,this.bottom));

    }
}
