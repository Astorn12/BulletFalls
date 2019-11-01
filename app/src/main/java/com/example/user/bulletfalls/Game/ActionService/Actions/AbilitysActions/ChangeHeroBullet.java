package com.example.user.bulletfalls.Game.ActionService.Actions.AbilitysActions;

import com.example.user.bulletfalls.Game.ActionService.Action;
import com.example.user.bulletfalls.Game.ActionService.ActionType.ActionType;
import com.example.user.bulletfalls.Game.Elements.Bullet.Specyfication.BulletSpecyfication;
import com.example.user.bulletfalls.Game.Management.EyeOnGame;

public class ChangeHeroBullet extends Action {
    BulletSpecyfication bulletSpecyfication;

    public ChangeHeroBullet(BulletSpecyfication bulletSpecyfication) {
        super(ActionType.INNER);
        this.bulletSpecyfication = bulletSpecyfication;
    }

    @Override
    public void doMagic(EyeOnGame eyeOnGame) {
        eyeOnGame.getHero().setBulletSpecyfication(bulletSpecyfication);

    }
}
