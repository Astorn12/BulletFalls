package com.example.user.bulletfalls.Game.ActionService.Actions;

import com.example.user.bulletfalls.Game.ActionService.Action;
import com.example.user.bulletfalls.Game.ActionService.ActionType.ActionType;
import com.example.user.bulletfalls.Game.Elements.BulletManiputatorsPackage.DefenceFilter;
import com.example.user.bulletfalls.Game.Elements.BulletManiputatorsPackage.DeffenceFilters.Resistance;
import com.example.user.bulletfalls.Game.Management.EyeOnGame;

import java.util.List;

public class BoostResistance extends BoostAction {


    public BoostResistance(ActionType type, int boost) {
        super(type, boost);
    }

    @Override
    public void doMagic(EyeOnGame eyeOnGame) {

        eyeOnGame.getHero().getAttackDefenceFilter().boostDefence(new Resistance(2,2));

    }
}
