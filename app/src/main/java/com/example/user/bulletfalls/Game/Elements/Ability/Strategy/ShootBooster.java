package com.example.user.bulletfalls.Game.Elements.Ability.Strategy;

import android.widget.LinearLayout;

import com.example.user.bulletfalls.Game.ActionService.Action;
import com.example.user.bulletfalls.Game.ActionService.ActionType.ActionType;
import com.example.user.bulletfalls.Game.ActionService.Actions.AttackFilterBoost;
import com.example.user.bulletfalls.Game.Activities.Game;
import com.example.user.bulletfalls.Game.Elements.BulletManiputatorsPackage.AttackFilters.MultiShoot;
import com.example.user.bulletfalls.Game.Elements.Bullet.Specyfication.BulletSpecyfication;
import com.example.user.bulletfalls.Game.Management.EyeOnGame;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("shootbooster")
public class ShootBooster implements StartAction {

    BulletSpecyfication bulletSpecyfication;
    private int licznik;
    public ShootBooster(BulletSpecyfication bullet)
    {
        this.bulletSpecyfication=bullet;
        this.licznik=1;
    }
    private ShootBooster(){
        licznik=1;
    }

    @Override
    public Action prepareAction(EyeOnGame eyeOnGame) {
        licznik++;
        return new AttackFilterBoost(ActionType.INNER,new MultiShoot(licznik,bulletSpecyfication));

    }


    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public void setAdditionalDescription(LinearLayout linearLayout) {

    }

    public BulletSpecyfication getBulletSpecyfication() {
        return bulletSpecyfication;
    }

    public void setBulletSpecyfication(BulletSpecyfication bulletSpecyfication) {
        this.bulletSpecyfication = bulletSpecyfication;
    }
}
