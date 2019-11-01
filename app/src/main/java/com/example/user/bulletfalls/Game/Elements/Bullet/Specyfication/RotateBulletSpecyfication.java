package com.example.user.bulletfalls.Game.Elements.Bullet.Specyfication;

import android.content.Context;

import com.example.user.bulletfalls.Game.Elements.Bullet.RotateBullet;
import com.example.user.bulletfalls.Game.Elements.Helper.Statistics.Active.BulletAS;
import com.example.user.bulletfalls.Game.Elements.Helper.Statistics.Collection.BulletCS;
import com.example.user.bulletfalls.Game.Elements.Helper.Statistics.Passive.BulletPS;
import com.example.user.bulletfalls.Game.Elements.Helper.Statistics.View.DynamicVS;
import com.example.user.bulletfalls.GlobalUsage.Enums.BE;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("rotatebulletspecyfication")
public class RotateBulletSpecyfication extends BulletSpecyfication {
    int rotationSpeed;

    public RotateBulletSpecyfication(BE bulletName, DynamicVS dynamicVS, BulletPS bulletPS, BulletAS bulletAS, BulletCS bulletCS) {
        super(bulletName, dynamicVS, bulletPS, bulletAS, bulletCS);
    }


    public int getRotationSpeed() {
        return rotationSpeed;
    }

    public void setRotationSpeed(int rotationSpeed) {
        this.rotationSpeed = rotationSpeed;
    }
    /*@Override
    public RotateBullet getConvertedBullet(Context context)
    {
        return new RotateBullet(context,this);
    }*/


}
