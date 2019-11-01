package com.example.user.bulletfalls.Game.Elements.Ability.Strategy;

import com.example.user.bulletfalls.Game.ActionService.Action;
import com.example.user.bulletfalls.Game.ActionService.ActionType.ActionType;
import com.example.user.bulletfalls.Game.ActionService.Actions.AttackFilterBoost;
import com.example.user.bulletfalls.Game.Elements.Bullet.Specyfication.BulletSpecyfication;
import com.example.user.bulletfalls.Game.Elements.Helper.Character;
import com.example.user.bulletfalls.Game.Management.EyeOnGame;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("timechangebullet")

public class TimeChangeBullet extends ChangeBullet {
    long actingTime;
    @JsonIgnore
    long startingTime;
    @JsonIgnore
    Character character;
    @JsonIgnore
    BulletSpecyfication standardBullet;
    public TimeChangeBullet(BulletSpecyfication bullet, int actingTime)
    {
        super(bullet);
       this.setActingTime(actingTime);
    }

    public TimeChangeBullet()
    {

    }

    @Override
    public Action prepareAction(EyeOnGame eyeOnGame) {

       return new AttackFilterBoost(ActionType.INNER, new com.example.user.bulletfalls.Game.Elements.BulletManiputatorsPackage.AttackFilters.TimeChangeBullet(this.actingTime,this.bulletSpecyfication));
    }

    public int getActingTime() {
        return (int)(actingTime/1000);
    }

    public void setActingTime(int actingTime) {
        this.actingTime =1000*actingTime;
    }


}
