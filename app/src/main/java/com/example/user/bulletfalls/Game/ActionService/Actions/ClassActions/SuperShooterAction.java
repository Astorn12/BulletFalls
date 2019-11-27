package com.example.user.bulletfalls.Game.ActionService.Actions.ClassActions;

import com.example.user.bulletfalls.Game.ActionService.ActionType.ActionType;
import com.example.user.bulletfalls.Game.ActionService.Actions.AbilitysActions.ChangeHeroBullet;
import com.example.user.bulletfalls.Game.ActionService.Actions.AbilitysActions.SuperShoot;
import com.example.user.bulletfalls.Game.Elements.Bullet.Specyfication.BulletSpecyfication;
import com.example.user.bulletfalls.Game.Management.EyeOnGame;

public class SuperShooterAction extends ClassAction {
    BulletSpecyfication bulletSpecyfication;

    public SuperShooterAction(int timeQuand,BulletSpecyfication bulletSpecyfication) {
        super(timeQuand);
        this.bulletSpecyfication=bulletSpecyfication;
    }

    @Override
    protected void doClassAction(EyeOnGame eog) {
        eog.addAction(new SuperShoot(ActionType.INNER,this.bulletSpecyfication));
    }

}
