package com.example.user.bulletfalls.Game.ActionService.Actions;

import com.example.user.bulletfalls.Game.ActionService.Action;
import com.example.user.bulletfalls.Game.ActionService.ActionType.ActionType;
import com.example.user.bulletfalls.Game.Elements.BulletManiputatorsPackage.AttackFilter;
import com.example.user.bulletfalls.Game.Management.EyeOnGame;

public class AttackFilterBoost extends Action {
    AttackFilter attackFilter;

    public AttackFilterBoost(ActionType type, AttackFilter attackFilter) {
        super(type);
        this.attackFilter = attackFilter;

    }

    @Override
    public void doMagic(EyeOnGame eyeOnGame) {
        eyeOnGame.getHero().getAttackDefenceFilter().boostAttack(this.attackFilter);
    }
}
