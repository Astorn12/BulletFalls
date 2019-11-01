package com.example.user.bulletfalls.Game.ActionService.Actions;

import com.example.user.bulletfalls.Game.ActionService.Action;
import com.example.user.bulletfalls.Game.ActionService.ActionType.ActionType;
import com.example.user.bulletfalls.Game.Management.EyeOnGame;

public class MultiplyHeroBullet extends Action {
    float f;
    public MultiplyHeroBullet(ActionType type,float f) {
        super(type);
        this.f=f;
    }

    @Override
    public void doMagic(EyeOnGame eyeOnGame) {
        eyeOnGame.getHero().getBulletSpecyfication().multiplyPower(f);
    }
}
