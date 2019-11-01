package com.example.user.bulletfalls.Game.ActionService.Actions;

import com.example.user.bulletfalls.Game.ActionService.Action;
import com.example.user.bulletfalls.Game.ActionService.ActionType.ActionType;
import com.example.user.bulletfalls.Game.ActionService.ActionType.ActionTypeBE;
import com.example.user.bulletfalls.Game.ActionService.ActionType.ActionTypeBI;
import com.example.user.bulletfalls.Game.Management.EyeOnGame;
import com.example.user.bulletfalls.Game.Management.GameController;

public class BoostHeroLife extends BoostAction {

    public BoostHeroLife(ActionTypeBI type, int boost) {
        super(ActionType.convert(type), boost);
    }

    @Override
    public void doMagic(EyeOnGame eyeOnGame) {
       eyeOnGame.getHero().boostLife(this.boost);
    }
}
