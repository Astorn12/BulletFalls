package com.example.user.bulletfalls.Game.ActionService.Actions.AbilitysActions;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.user.bulletfalls.Game.ActionService.Action;
import com.example.user.bulletfalls.Game.ActionService.ActionType.ActionType;
import com.example.user.bulletfalls.Game.Elements.Bullet.Bullet;
import com.example.user.bulletfalls.Game.Elements.BulletManiputatorsPackage.DefenceFilter;
import com.example.user.bulletfalls.Game.Elements.BulletManiputatorsPackage.DeffenceFilters.ShieldStot;
import com.example.user.bulletfalls.Game.Elements.Weapon.Weapon;
import com.example.user.bulletfalls.Game.Management.EyeOnGame;
import com.example.user.bulletfalls.GlobalUsage.Enums.Shape;
import com.example.user.bulletfalls.R;

public class AngelProtector extends Action {
    long time;
    private long startingTime;
    int resouerces;

    protected DefenceFilter defenceFilter;
    public AngelProtector(int seconds, int resources) {
        super(ActionType.INNER);
        this.time = seconds * 1000;
        this.startingTime = 0;
        this.resouerces = resources;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void doMagic(EyeOnGame eyeOnGame) {
        if(this.startingTime==0){
            this.startingTime=System.currentTimeMillis();
            Weapon weapon=new Weapon(eyeOnGame.getHero().getContext(),this.resouerces, Shape.CIRCLE);
            eyeOnGame.getHero().addWeapon(weapon);
            eyeOnGame.getHero().setImmune(true);
            this.setPermanentAction();
            this.defenceFilter=new ShieldStot(weapon);
            eyeOnGame.getHero().getAttackDefenceFilter().boostDefence(defenceFilter);
            this.defenceFilter.setRemovAble(false);

        }else if(System.currentTimeMillis()-this.startingTime>this.time){

            eyeOnGame.getHero().setImmune(false);
            eyeOnGame.getHero().removeWeapon(this.resouerces);
            defenceFilter.setRemovAble(true);
            this.endActionDuration();
           // this.startingTime=0;
        }else{

        }
    }
}
