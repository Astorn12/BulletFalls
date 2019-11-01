package com.example.user.bulletfalls.Game.ActionService.Actions.IfActions;

import com.example.user.bulletfalls.Game.ActionService.Action;
import com.example.user.bulletfalls.Game.ActionService.ActionType.ActionType;
import com.example.user.bulletfalls.Game.Elements.Bullet.Bullet;
import com.example.user.bulletfalls.Game.Management.EyeOnGame;

import java.util.List;

public class ShootGun extends Action {
    List<Bullet> bullets;

    public ShootGun(ActionType type, List<Bullet> bullets) {
        super(type);
        this.bullets = bullets;
    }

    @Override
    public void doMagic(EyeOnGame eyeOnGame) {

    }
}
