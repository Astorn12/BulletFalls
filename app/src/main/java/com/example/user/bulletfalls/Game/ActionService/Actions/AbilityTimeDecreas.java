package com.example.user.bulletfalls.Game.ActionService.Actions;

import android.app.Activity;

import com.example.user.bulletfalls.Game.ActionService.Action;
import com.example.user.bulletfalls.Game.ActionService.ActionType.ActionType;
import com.example.user.bulletfalls.Game.Elements.Ability.Specyfication.AbilitySpecyfication;
import com.example.user.bulletfalls.Game.Elements.Hero.Hero;
import com.example.user.bulletfalls.Game.Management.EyeOnGame;

public class AbilityTimeDecreas extends BoostAction {


    public AbilityTimeDecreas(ActionType type, int boost) {
        super(type, boost);
    }

    @Override
    public void doMagic(EyeOnGame eyeOnGame) {
        Hero hero= eyeOnGame.getHero();
        for(AbilitySpecyfication ability:hero.getAbilities().getAbilities())
        {
            ability.decreaseRenewalTime(((double)boost)/100);
        }
    }
}
