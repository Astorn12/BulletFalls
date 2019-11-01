package com.example.user.bulletfalls.Game.ActionService.Actions.AbilitysActions;

import com.example.user.bulletfalls.Game.ActionService.Action;
import com.example.user.bulletfalls.Game.ActionService.ActionType.ActionType;
import com.example.user.bulletfalls.Game.ActionService.Actions.BoostAction;
import com.example.user.bulletfalls.Game.Elements.Hero.Hero;
import com.example.user.bulletfalls.Game.Management.EyeOnGame;

public class HealAction extends BoostAction {


    public HealAction( int boost) {
        super(ActionType.INNER, boost);
    }

    @Override
    public void doMagic(EyeOnGame eyeOnGame) {
        Hero hero=eyeOnGame.getHero();
        if(hero.getName().equals("Soos"))
        {
            hero.heal(getBoost()*2);
        }
        else hero.heal(getBoost());

    }
}
