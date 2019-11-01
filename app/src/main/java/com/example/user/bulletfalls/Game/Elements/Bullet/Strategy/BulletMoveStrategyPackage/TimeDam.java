package com.example.user.bulletfalls.Game.Elements.Bullet.Strategy.BulletMoveStrategyPackage;

import android.graphics.Point;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("timedam")
public class TimeDam  extends Dam{
    int lifeTime;


    public TimeDam()
    {

    }
    public TimeDam(int position, int lifeTime)
    {
        super(position);
        this.lifeTime=lifeTime;
    }
    @Override
    public Point getQuantum(int speed, Point current) {

        if(lifeTime>0)lifeTime--;
        else return new Point(10000,10000);
        if(current.x+speed>position)
        {
            return new Point(position-current.x,0);
        }
        else
        {

            return new Point(speed,0);
        }

    }
    @Override
    public Dam clone()
    {
        return new TimeDam(this.position,this.lifeTime);
    }
    public int getLifeTime() {
        return lifeTime;
    }

    public void setLifeTime(int lifeTime) {
        this.lifeTime = lifeTime;
    }
}
