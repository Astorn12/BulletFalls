package com.example.user.bulletfalls.Game.ActionService.Actions.AbilitysActions;

import com.example.user.bulletfalls.Game.ActionService.Action;
import com.example.user.bulletfalls.Game.ActionService.ActionType.ActionType;
import com.example.user.bulletfalls.Game.Elements.Bullet.Specyfication.BulletSpecyfication;
import com.example.user.bulletfalls.Game.Elements.BulletManiputatorsPackage.AttackFilters.Clip;
import com.example.user.bulletfalls.Game.Management.EyeOnGame;

public class SuperShoot extends Action {
    BulletSpecyfication bulletSpecyfication;

    public SuperShoot(ActionType type, BulletSpecyfication bulletSpecyfication) {
        super(type);
        this.bulletSpecyfication = bulletSpecyfication;
    }

    @Override
    public void doMagic(EyeOnGame eyeOnGame) {
        eyeOnGame.getHero().getAttackDefenceFilter().boostAttack(new Clip(1,this.bulletSpecyfication));
    }
}
