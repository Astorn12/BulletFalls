package com.example.user.bulletfalls.Game.ActionService.Actions;

import com.example.user.bulletfalls.Game.ActionService.Action;
import com.example.user.bulletfalls.Game.ActionService.ActionType.ActionType;
import com.example.user.bulletfalls.Game.Management.EyeOnGame;

public class MultiplyHeroLife extends Action {
    float f;
    public MultiplyHeroLife(ActionType type,float f) {
        super(type);
        this.f=f;
    }

    @Override
    public void doMagic(EyeOnGame eyeOnGame) {
        int hp=eyeOnGame.getHero().getLife();
        eyeOnGame.getHero().multiplyLife(f);
    }
}
