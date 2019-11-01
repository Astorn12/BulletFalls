package com.example.user.bulletfalls.Game.ActionService.Actions;

import com.example.user.bulletfalls.Game.ActionService.ActionType.ActionType;
import com.example.user.bulletfalls.Game.ActionService.ActionType.ActionTypeBI;
import com.example.user.bulletfalls.Game.Management.EyeOnGame;

public class PowerUpBullet extends BoostAction {


    public PowerUpBullet(ActionTypeBI type, int boost) {
        super(ActionType.convert(type), boost);
    }

    @Override
    public void doMagic(EyeOnGame eyeOnGame) {
        eyeOnGame.getHero().getBulletSpecyfication().increasePower(boost);
    }
}
