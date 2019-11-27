package com.example.user.bulletfalls.Game.GameBiznesFunctions.Classes;

import com.example.user.bulletfalls.Game.ActionService.Actions.ClassActions.ClassAction;
import com.example.user.bulletfalls.Game.ActionService.Actions.ClassActions.SuperShooterAction;
import com.example.user.bulletfalls.Game.Elements.Bullet.Specyfication.BulletSpecyfication;
import com.example.user.bulletfalls.Game.Management.EyeOnGame;
import com.example.user.bulletfalls.GlobalUsage.Enums.BE;
import com.example.user.bulletfalls.R;
import com.example.user.bulletfalls.Storage.Sets.BulletSet;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.Arrays;

@JsonTypeName("supershooter")
public class SuperShooter extends MasterAbility {

    BulletSpecyfication bulletSpecyfication;

    public SuperShooter() {

        this.levelTable=new LevelTable( Arrays.asList(
                new LevelBoost(4,10),
                new LevelBoost(10,12),
                new LevelBoost(15,15),
                new LevelBoost(20,30),
                new LevelBoost(30,50),
                new LevelBoost(50,100)
        ));
        this.bulletSpecyfication= BulletSet.getInstance().getBullet(BE.RED);
    }

    @Override
    public int getImage() {
        return R.drawable.shooter;
    }

    @Override
    public String getDescription() {
        return "Super Shooterzy to mistrzowie w fachu strzelecki, zwiększają oni moc co trzeciego strzłu, lepiej niech przeciwnik liczy " +
                "liczy kulki, aby znowy nie oberwać srogim pociskiem.";
    }

    @Override
    public ClassAction action(EyeOnGame eog) {

        return new SuperShooterAction(this.timeQuant,this.bulletSpecyfication);
    }
}
