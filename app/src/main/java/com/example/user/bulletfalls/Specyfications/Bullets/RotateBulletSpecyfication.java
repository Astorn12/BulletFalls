package com.example.user.bulletfalls.Specyfications.Bullets;

import android.content.Context;

import com.example.user.bulletfalls.Objects.RotateBullet;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("rotatebulletspecyfication")
public class RotateBulletSpecyfication extends BulletSpecyfication{
    int rotationSpeed;
    public RotateBulletSpecyfication()
    {
        super();

    }
    public RotateBulletSpecyfication(RotateBullet rotateBullet)
    {
        super(rotateBullet);
        this.rotationSpeed=rotateBullet.getRotationSpeed();
    }

    public int getRotationSpeed() {
        return rotationSpeed;
    }

    public void setRotationSpeed(int rotationSpeed) {
        this.rotationSpeed = rotationSpeed;
    }
    @Override
    public RotateBullet getConvertedBullet(Context context)
    {
        return new RotateBullet(context,this);
    }


}
