package com.example.user.bulletfalls.Game.ActionService.Actions;

import com.example.user.bulletfalls.Game.ActionService.Action;
import com.example.user.bulletfalls.Game.ActionService.ActionType.ActionType;
import com.example.user.bulletfalls.Game.Elements.BulletManiputatorsPackage.AttackFilter;
import com.example.user.bulletfalls.Game.Elements.BulletManiputatorsPackage.DefenceFilter;
import com.example.user.bulletfalls.Game.Management.EyeOnGame;

public class DefenceFilterBoost extends Action {
    DefenceFilter defenceFilter;

    public DefenceFilterBoost(ActionType type, DefenceFilter defenceFilter) {
        super(type);
        this.defenceFilter = defenceFilter;

    }

    @Override
    public void doMagic(EyeOnGame eyeOnGame) {
        eyeOnGame.getHero().getAttackDefenceFilter().boostDefence(this.defenceFilter);

    }
}
