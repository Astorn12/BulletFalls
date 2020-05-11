package com.example.user.bulletfalls.Game.Elements.Bullet.Strategy.BulletDoToCharacterStrategyPackage;

import android.widget.LinearLayout;

import com.example.user.bulletfalls.Game.Elements.Helper.Character;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("rotationcrit")
public class RotationCrit implements BulletDoToCharacterStrategy {

    int rotationMeter;
    int power;

    int finalDamage;
    @Override
    public void doToCharacter(Character character) {
        finalDamage=this.power;
        if(rotationMeter==45) {
            finalDamage *= 10;
        }
        else if(rotationMeter>10&&rotationMeter<45) {
            finalDamage*=3;
        }
        else if((rotationMeter>45&&rotationMeter<=90)||(rotationMeter<=10&&rotationMeter>315))
        {
            finalDamage*=(int)((float)finalDamage*1.5);

        }
        else if((rotationMeter>270&&rotationMeter<=315)||(rotationMeter>90&&rotationMeter<=120))
        {
            finalDamage=(int)((float)finalDamage*0.9);

        }
        else if(rotationMeter>120&&rotationMeter<=270)
        {
            finalDamage=(int)((float)finalDamage/2);
        }
        ///finalDamage+=rotationSpeed;
        finalDamage-=this.power;
        if(finalDamage<0) finalDamage=0;
        else
        character.getDamage(finalDamage);

    }

    @Override
    public BulletDoToCharacterStrategy clone() {
        return new RotationCrit();
    }

    @Override
    public void showOwnDescription(LinearLayout linearLayout) {

    }


    public void setFinalRotate(int finalRotate){
        this.rotationMeter=finalRotate;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getFinalDamage() {
        return finalDamage;
    }
}
