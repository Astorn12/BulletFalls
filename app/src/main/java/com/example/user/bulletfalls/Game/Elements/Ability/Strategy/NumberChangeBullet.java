package com.example.user.bulletfalls.Game.Elements.Ability.Strategy;

import com.example.user.bulletfalls.Game.ActionService.Action;
import com.example.user.bulletfalls.Game.ActionService.ActionType.ActionType;
import com.example.user.bulletfalls.Game.ActionService.Actions.AttackFilterBoost;
import com.example.user.bulletfalls.Game.Elements.Bullet.Specyfication.BulletSpecyfication;
import com.example.user.bulletfalls.Game.Elements.BulletManiputatorsPackage.AttackFilters.Clip;
import com.example.user.bulletfalls.Game.Elements.Helper.Character;
import com.example.user.bulletfalls.Game.Management.EyeOnGame;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("numberchangebullet")
public class NumberChangeBullet extends ChangeBullet {
    int clip;
    @JsonIgnore
    int currentAmunition;
    @JsonIgnore
    Character character;


    public NumberChangeBullet(BulletSpecyfication bullet, int clip)
    {
        super(bullet);
        this.clip=clip;
    }

    @Override
    public Action prepareAction(EyeOnGame eyeOnGame) {

       return new AttackFilterBoost(ActionType.INNER,new Clip(clip,this.bulletSpecyfication));

    }


    public int getClip() {
        return clip;
    }

    public void setClip(int clip) {
        this.clip = clip;
    }


}
