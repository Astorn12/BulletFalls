package com.example.user.bulletfalls.Strategies.Abilities.TimeCounting;

import android.graphics.Point;
import android.widget.LinearLayout;

import com.example.user.bulletfalls.Enums.BE;
import com.example.user.bulletfalls.GameManagement.Game;
import com.example.user.bulletfalls.GameManagement.GameController;
import com.example.user.bulletfalls.Objects.Bullet;
import com.example.user.bulletfalls.Objects.Character;
import com.example.user.bulletfalls.Sets.BulletSet;
import com.example.user.bulletfalls.Specyfications.Dynamic.Bullets.BulletSpecyfication;
import com.example.user.bulletfalls.Strategies.Abilities.DoToCharacterStrategy;
import com.example.user.bulletfalls.Strategies.Abilities.PositioningSupporters.MultiBulletPositioner;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.List;

@JsonTypeName("fullcounter")

public class FullCounter implements DoToCharacterStrategy {
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
    public void doToCharacter(Character character) {
        long reduction= counterTime-System.currentTimeMillis()+this.lastTimedUse;
        long jumpTime=this.counterTime;

        if(reduction>0)
            jumpTime-=reduction;

        GameController gc=((Game) character.getContext()).getController();

        int damageToCounter=gc.getMedium().timeJumpHeal(jumpTime);
        damageToCounter*=multiplier;
        System.out.println("Damage to counter: "+damageToCounter);
        Bullet bs= BulletSet.getBullet(BE.COUNTERBULLET);

        bs=bs.changeContext(character.getContext());
        bs.setPower(this.bulletPower);
        bs.setFrame(character.getFrame());

        int numberOfBullets= damageToCounter/bulletPower;
        System.out.println("Number of bullets: "+numberOfBullets );

        MultiBulletPositioner m= new MultiBulletPositioner();

        /*for(Bullet b: m.steadilyVerticalPositioning(bs,numberOfBullets,character))
        {
            b.born();
            gc.addBullet(b);
        }*/
        character.launchMultiBullets(m.steadilyVerticalPositioning(bs,numberOfBullets,character));


       /* if(numberOfBullets>0) {


            int gameScreenHight = character.getFrame().getHeight();
            int jump=gameScreenHight/numberOfBullets+1;
            int position=0;
            for (int i = 0; i < numberOfBullets; i++) {
                Bullet clone = bs.clone();

                position+=jump;

                clone.setStartingPoint(new Point(character.getStartingPointForBullet().x, position));
                clone.born();
                gc.addBullet(clone);

            }

        }*/
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
