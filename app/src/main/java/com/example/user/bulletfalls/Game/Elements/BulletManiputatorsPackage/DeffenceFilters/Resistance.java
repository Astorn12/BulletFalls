package com.example.user.bulletfalls.Game.Elements.BulletManiputatorsPackage.DeffenceFilters;

import com.example.user.bulletfalls.Game.Elements.Bullet.Bullet;
import com.example.user.bulletfalls.Game.Elements.BulletManiputatorsPackage.DefenceFilter;
import com.example.user.bulletfalls.Game.Elements.Helper.Character;

public class Resistance extends DefenceFilter {
    int resistantValue;
    int bottom;

    public Resistance(int resistantValue, int bottom) {
        this.resistantValue = resistantValue;
        this.bottom = bottom;
    }


    @Override
    public void filter(Bullet bullet, Character character) {
        if(bottom<bullet.getPower()-resistantValue)
        {
            bullet.decreasePower(resistantValue);
        }else if(bullet.getPower()<bottom){

        }else{
            bullet.decreasePower(bullet.getPower()-bottom);
        }
    }


    public void boost(int x)
    {
        this.bottom--;
        this.resistantValue+=x;
    }

    public void suppress(int x) {
        this.resistantValue-=x;
        this.bottom++;
        if(this.resistantValue<0) resistantValue=0;
    }

    /**GETTERS & SETTERS*/

    public int getResistantValue() {
        return resistantValue;
    }

    public void setResistantValue(int resistantValue) {
        this.resistantValue = resistantValue;
    }

    public int getBottom() {
        return bottom;
    }

    public void setBottom(int bottom) {
        this.bottom = bottom;
    }

    @Override
    public String toString()
    {
        return this.resistantValue+" to "+ this.bottom;
    }
}
