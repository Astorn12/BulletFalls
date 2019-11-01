package com.example.user.bulletfalls.Game.Elements.Ability.Strategy.TimeCounting;

import android.widget.LinearLayout;

import com.example.user.bulletfalls.Game.ActionService.Action;
import com.example.user.bulletfalls.Game.ActionService.ActionType.ActionType;
import com.example.user.bulletfalls.Game.ActionService.Actions.AttackFilterBoost;
import com.example.user.bulletfalls.Game.Elements.Ability.Strategy.StartAction;
import com.example.user.bulletfalls.Game.Elements.Bullet.Specyfication.BulletSpecyfication;
import com.example.user.bulletfalls.Game.Elements.BulletManiputatorsPackage.AttackFilters.ShootGun;
import com.example.user.bulletfalls.Game.Management.EyeOnGame;
import com.example.user.bulletfalls.GlobalUsage.Enums.BE;
import com.example.user.bulletfalls.Game.Activities.Game;
import com.example.user.bulletfalls.Game.Management.GameController;
import com.example.user.bulletfalls.Game.Elements.Bullet.Bullet;
import com.example.user.bulletfalls.Storage.Sets.BulletSet;
import com.example.user.bulletfalls.Game.Elements.Ability.Strategy.PositioningSupporters.MultiBulletPositioner;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("fullcounter")

public class FullCounter implements StartAction {
    int bulletPower;
    float multiplier;
    long counterTime;
    long lastTimedUse;

    private FullCounter() {
    }

    public FullCounter(int bulletPower, long counterTime,float multiplier) {
        this.bulletPower = bulletPower;
        this.counterTime = counterTime;
        this.multiplier=multiplier;
    }

    public FullCounter(int bulletPower, long counterTime) {
        this.bulletPower = bulletPower;
        this.counterTime = counterTime;
        this.multiplier=1.0f;
    }

    @Override
    public Action prepareAction(EyeOnGame eyeOnGame) {
        long reduction= counterTime-System.currentTimeMillis()+this.lastTimedUse;
        long jumpTime=this.counterTime;

        if(reduction>0)
            jumpTime-=reduction;

        GameController gc=((Game) eyeOnGame.getHero().getContext()).getController();

        int damageToCounter=gc.getMedium().timeJumpHeal(jumpTime);
        damageToCounter*=multiplier;
        System.out.println("Damage to counter: "+damageToCounter);
        BulletSpecyfication bs= BulletSet.getInstance().getBullet(BE.COUNTERBULLET);

        Bullet b2=  new Bullet(eyeOnGame.getHero().getContext(),bs);
        b2.setPower(this.bulletPower);
        b2.setFrame(eyeOnGame.getHero().getFrame());

        int numberOfBullets= damageToCounter/bulletPower;
        System.out.println("Number of bullets: "+numberOfBullets );

        MultiBulletPositioner m= new MultiBulletPositioner();

        return new AttackFilterBoost(ActionType.INNER, new ShootGun(m.steadilyVerticalPositioning(b2,numberOfBullets, eyeOnGame.getHero())));
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public void setAdditionalDescription(LinearLayout linearLayout) {

    }

    public int getBulletPower() {
        return bulletPower;
    }

    public void setBulletPower(int bulletPower) {
        this.bulletPower = bulletPower;
    }

    public long getCounterTime() {
        return counterTime;
    }

    public void setCounterTime(long counterTime) {
        this.counterTime = counterTime;
    }

    public long getLastTimedUse() {
        return lastTimedUse;
    }

    public void setLastTimedUse(long lastTimedUse) {
        this.lastTimedUse = lastTimedUse;
    }

    public float getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(float multiplier) {
        this.multiplier = multiplier;
    }
}
