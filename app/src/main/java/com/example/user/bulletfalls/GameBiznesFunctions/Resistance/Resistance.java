package com.example.user.bulletfalls.GameBiznesFunctions.Resistance;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("resistance")
public class Resistance implements IResistance {
    int resistantValue;
    int bottom;

    public Resistance(int resistantValue, int bottom) {
        this.resistantValue = resistantValue;
        this.bottom = bottom;
    }

    private Resistance() {
    }

    @Override
    public int reduce(int damage) {
        if(bottom<damage-resistantValue)
        {
            return damage-resistantValue;
        }else if(damage<bottom){
            return damage;
        }else{
            return bottom;
        }
    }

    public void boost(int x)
    {
        this.bottom--;
        this.resistantValue+=x;
    }

    @Override
    public void suppress(int x) {
        this.resistantValue-=x;
        this.bottom++;
        if(this.resistantValue<0) resistantValue=0;
    }

    @Override
    public IResistance clone() {
        return new Resistance(this.resistantValue,this.bottom);
    }

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
